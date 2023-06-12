package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.File;

public class Main extends Application {
    private ListView<String> fileList;
    private Button selectFolderBtn;
    private Button performTaskBtn;
    private Label folderLabel;
    private String selectedFolderPath; // Variable to store the selected folder path

    private final static String documentFolder = "Documents";
    private final static String imageFolder = "Images";
    private final static String videoFolder = "Videos";
    private final static String compressedFolder = "Compressed";
    private final static String sqlqueryFolder = "SQL_Queries";
    private final static String programFolder = "Programs";
    private final static String tableauFolder = "TableauWorksheet";
    // private final String documentFolder = "Documents";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Organizer v1.0");

        // Create the UI components
        fileList = new ListView<>();
        selectFolderBtn = new Button("Select Folder");
        performTaskBtn = new Button("Organize");
        performTaskBtn.setDisable(true); // Disable the button initially

        folderLabel = new Label();

        // Handle button actions
        selectFolderBtn.setOnAction(e -> selectFolder());
        performTaskBtn.setOnAction(e -> performTask());

        // Create the button container
        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(selectFolderBtn, performTaskBtn);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(buttonContainer, folderLabel, fileList);

        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedFolder = directoryChooser.showDialog(null);

        if (selectedFolder != null) {
            selectedFolderPath = selectedFolder.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\");
            listFiles(selectedFolder);
            performTaskBtn.setDisable(false); // Enable the button when a folder is selected
            folderLabel.setText("Selected Folder: " + selectedFolderPath);
        }
    }

    private void listFiles(File folder) {
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            // Clear the file list
            fileList.getItems().clear();

            // Add file names to the list
            for (File file : files) {
                if (file.isFile()) {
                    fileList.getItems().add(file.getName());
                }
            }
        } else {
            System.out.println("Invalid folder path or the folder does not exist.");
        }
    }

    private static String getFileExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        if(i > 0) {
            extension = filename.substring(i + 1);
        }
        return extension.toLowerCase();
    }

    private void performTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate some task
                // Thread.sleep(3000);
                // return null;
                // selectedFolderPath
                File directory = new File(selectedFolderPath);
                File[] files = directory.listFiles();

                for(File file : files) {
                    if (file.isFile()) {
                        String extension = getFileExtension(file.getName());
                        // Documents
                        if(
                                extension.equals("docx") ||
                                        extension.equals("pptx") ||
                                        extension.equals("xlsx") ||
                                        extension.equals("xls") ||
                                        extension.equals("csv") ||
                                        extension.equals("pdf") ||
                                        extension.equals("doc") ||
                                        extension.equals("txt")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + documentFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if(
                            // Images
                                extension.equals("jpeg") ||
                                        extension.equals("jpg") ||
                                        extension.equals("png") ||
                                        extension.equals("PNG") ||
                                        extension.equals("gif")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + imageFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (
                            // Videos
                                extension.equals("mp4") ||
                                        extension.equals("mov")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + videoFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (
                            // compressed
                                extension.equals("zip") ||
                                        extension.equals("rar") ||
                                        extension.equals("7z") ||
                                        extension.equals("bzip2") ||
                                        extension.equals("tar") ||
                                        extension.equals("gz")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + compressedFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (
                            // SQL QUERIES
                                extension.equals("sql")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + sqlqueryFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (
                            // compressed
                                extension.equals("exe") || extension.equals("msi")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + programFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (
                            // Tableau Dashboard 
                                extension.equals("twbx")
                        ) {
                            File folder = new File(directory.getAbsolutePath() + '/' + tableauFolder);
                            if(!folder.exists()) {
                                folder.mkdirs();
                            }

                            Path sourcePath = Paths.get(file.getAbsolutePath());
                            Path targetPath = Paths.get(folder.getAbsolutePath() + "/" + file.getName());
                            try {
                                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            // Display a pop-up message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Organization Completed");
            alert.setHeaderText(null);
            alert.setContentText("Files have been organized!");
            alert.showAndWait();
        });

        // Start the task on a separate thread
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void stop() {
        // Stop the JavaFX application when the window is closed
        Platform.exit();
        System.exit(0);
    }
}
