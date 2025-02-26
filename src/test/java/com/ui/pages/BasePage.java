package com.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ui.settings.ProjectSettings;
import io.qameta.allure.Step;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void click(String selector){
        page.click(selector);
    }

    @Step("Ожидание загрузки в секундах")
    public void waitLoading(int timeOutSec) {
        for (int i = 0; i < timeOutSec; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Подтягивание файлов из файловой системы")
    protected void sendFiles(String[] fileNames, Locator input) {
        Path[] paths = Arrays.stream(fileNames)
                .map(Paths::get)  // Преобразуем каждую строку в Path
                .toArray(Path[]::new);

        input.setInputFiles(paths);  // Передаем массив файлов
    }

    @Step("Сравнение скриншотов")
    public void imageComparison(String pathExp, String nameExp, String pathCur, String nameCur) throws IOException {

        int x = 0;
        int y = 0;
        int width = 1700;
        int height = 800;

        // Сохранение скриншота всей страницы
        String screenshotPath = pathCur + nameCur;
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(screenshotPath))
                .setFullPage(true));

        // Читаем сделанный скриншот
        BufferedImage fullScreenshot = ImageIO.read(new File(screenshotPath));

        // Обрезаем скриншот по заданным координатам
        BufferedImage croppedImage = fullScreenshot.getSubimage(x, y, width, height);

        // Сохраняем обрезанный скриншот
        File currentScreen = new File(screenshotPath);
        ImageIO.write(croppedImage, "PNG", currentScreen);

        // Загружаем ожидаемый скриншот
        BufferedImage expectedImage = ImageIO.read(new File(pathExp + nameExp));

        // Сравнение изображений
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage, croppedImage);

        int diffSize = diff.getDiffSize();
        int totalPixels = width * height;
        double similarity = (1 - (double) diffSize / totalPixels) * 100;

        System.out.println("Совпадение: " + similarity + "%");

        if (similarity < 95) {
            System.out.println("Есть отличия");

            ImageIO.write(diff.getMarkedImage(), "PNG",
                    new File(pathCur + "diff.png"));
        } else {
            System.out.println("Совпадают");
        }

    }

    @Step("Получение последнего загруженного (экспортированного) файла")
    public File getLatestDownloadedFile(String downloadDir){

        File dir = new File(downloadDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0){
            File latestFile = files[0];
            for (File file: files){
                if (file.lastModified() > latestFile.lastModified()){
                    latestFile = file;
                }
            }
            return latestFile;
        }
        return null;
    }

}
