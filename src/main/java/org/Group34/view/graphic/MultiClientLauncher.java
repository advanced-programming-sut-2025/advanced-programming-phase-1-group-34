package org.Group34.view.graphic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MultiClientLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Start the server in a separate process
        startServerProcess();

        // Wait for server to initialize
        TimeUnit.SECONDS.sleep(3);

        // Start client processes
        startClientProcess("Player 1", 100, 100);
        TimeUnit.SECONDS.sleep(1); // Stagger client startups
        startClientProcess("Player 2", 500, 100);
    }

    private static void startServerProcess() throws IOException {
        // Use a more robust way to start the server
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(
                javaBin,
                "-cp", classpath,
                "-Djava.awt.headless=true", // Run headless to avoid GUI conflicts
                "org.Group34.network.server.ServerLauncher"
        );
        pb.redirectErrorStream(true);
        Process serverProcess = pb.start();

        // Print server output for debugging
        new Thread(() -> {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(serverProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[SERVER] " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void startClientProcess(String title, int x, int y) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(
                javaBin,
                "-cp", classpath,
                "org.Group34.view.graphic.SingleClientLauncher",
                title,
                String.valueOf(x),
                String.valueOf(y),
                "12345"
        );
        pb.redirectErrorStream(true);
        Process clientProcess = pb.start();
    }
}