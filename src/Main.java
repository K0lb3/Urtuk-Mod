import kaleta.hex3.globals.HexConstants;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import kaleta.hex3.HexGame3;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.addIcon("urtuk-icon-128x128.png", Files.FileType.Internal);
        config.addIcon("urtuk-icon-32x32.png", Files.FileType.Internal);
        config.title = "Urtuk";
        config.width = 1920;
        config.height = 1080;
        config.vSyncEnabled = true;
        config.allowSoftwareMode = true;
        HexConstants.RELEASE_BUILD = false;
        HexConstants.DISCORD_ENABLED = false;

        Game game = new Game();

        new LwjglApplication((ApplicationListener) game, config);
    }
}
