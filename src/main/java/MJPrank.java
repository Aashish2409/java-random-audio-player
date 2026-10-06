import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.concurrent.*;

public class MJPrank {

    private static final String SOUND_FOLDER = "sounds";

    private static final int MIN_DELAY_SECONDS = 15;
    private static final int MAX_DELAY_SECONDS = 80;

    private static final Random random = new Random();

    public static void main(String[] args) {

        File soundFolder = new File(SOUND_FOLDER);

        if (!soundFolder.exists() || !soundFolder.isDirectory()) {
            System.out.println("Sounds folder not found.");
            return;
        }

        File[] sounds = soundFolder.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".mp3")
        );

        if (sounds == null || sounds.length == 0) {
            System.out.println("No MP3 files found.");
            return;
        }

        System.out.println("MJ Prank started!");

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduleNextSound(scheduler, sounds);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scheduler.shutdownNow();
        }
    }

    private static void scheduleNextSound(
            ScheduledExecutorService scheduler,
            File[] sounds) {

        int delay = random.nextInt(
                MAX_DELAY_SECONDS - MIN_DELAY_SECONDS + 1
        ) + MIN_DELAY_SECONDS;

        System.out.println(
                "Next sound in " + delay + " seconds..."
        );

        scheduler.schedule(() -> {

            File sound = sounds[random.nextInt(sounds.length)];

            System.out.println(
                    "Playing: " + sound.getName()
            );

            playSound(sound);

            scheduleNextSound(scheduler, sounds);

        }, delay, TimeUnit.SECONDS);
    }

    private static void playSound(File file) {

        try (FileInputStream inputStream =
                     new FileInputStream(file)) {

            Player player = new Player(inputStream);
            player.play();

        } catch (Exception e) {
            System.out.println(
                    "Could not play " + file.getName()
            );
        }
    }
}