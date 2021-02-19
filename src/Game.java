import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import kaleta.hex3.GlobalGameProgress;
import kaleta.hex3.HexGame3;
import kaleta.hex3.battle.BattlefieldResult;
import kaleta.hex3.battle.battleDifficulty.HellDifficulty;
import kaleta.hex3.battle.entities.HexEntity;
import kaleta.hex3.gameScreens.*;
import kaleta.hex3.log;
import kaleta.hex3.utils.HexGameException;
import kaleta.hex3.utils.cast;
import kaleta.hex3.world.World;
import kaleta.hex3.world.WorldBuilder;
import kaleta.hex3.world.Zone;
import kaleta.hex3.world.data.ArenaDefinition;
import kaleta.hex3.world.data.BattlefieldSettings;

public class Game extends HexGame3 {
    private boolean hasInitialized = false;
    private boolean isVSyncOn = true;
    Array<IGameScreen> screenStack = new Array();

    public void create() {
        // execute normal create
        super.create();
        // make some tweaks
        GlobalGameProgress current = GlobalGameProgress.current;
        current.zoneMaxTier.set(6);
        current.gameWonMaxDifficulty.set(HellDifficulty.rating);
        //current.totalLevelUPs.set(fromDisk.totalLevelUPs_internal);
        current.humanFactionWon.set(true);
        current.swampersFactionWon.set(true);
        current.vampiresFactionWon.set(true);
        current.beastsFactionWon.set(true);
        current.forsakenFactionWon.set(true);
        current.revenantsFactionWon.set(true);
        current.werebeastsFactionWon.set(true);
    }


    public void updateGameScreen(GameScreenAction gameScreenAction) {
        if (gameScreenAction == GameScreenAction.exit) {
            boolean handled = false;
            BattleMapScreen bm = as(BattleMapScreen.class, getCurrentGameScreen());
            if (bm != null && !handled) {
                log.info("Destroying battle map. Result: " + bm.getBattlefieldResult());
                if (bm.getBattlefieldResult() != BattlefieldResult.playing) {
                    popScreen();
                    WargameScreen wargameScreen = as(WargameScreen.class, getCurrentGameScreen());
                    if (wargameScreen != null) {
                        wargameScreen.world.battleRequest.handleBattleOver(bm.getBattlefieldResult());
                        handled = true;
                    }
                } else if (bm.gameScreenActionOnExit == GameScreenActionOnExit.openResumeMenu) {
                    pushScreen((IGameScreen)new MainMenuResumeScreen());
                    handled = true;
                } else if (bm.getGameScreenActionOnExit() == GameScreenActionOnExit.openUnitInfoScreen) {
                    pushScreen((IGameScreen)new UnitInfoScreen((HexEntity)bm.getGameScreenOnExitObject()));
                    handled = true;
                } else if (bm.gameScreenActionOnExit == GameScreenActionOnExit.loadDebugBattlefield) {
                    createAndPushDebugBattlemap_simple();
                    handled = true;
                } else if (bm.gameScreenActionOnExit == GameScreenActionOnExit.openEntityManagement) {
                    pushScreen((IGameScreen)new PartyScreen((bm.getBattlefield().getBattleSettings()).world));
                    handled = true;
                }
            }
            if (!handled) {
                MenuBaseScreen mainMenuScreen = as(MenuBaseScreen.class, getCurrentGameScreen());
                if (mainMenuScreen != null)
                    if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.startNewGame) {
                        pushScreen((IGameScreen)new GameSetupScreen());
                        handled = true;
                    } else if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.startNewGame_conquest) {
                        pushScreen((IGameScreen)new WargameScreen(WorldBuilder.buildConquest(0)));
                        handled = true;
                    } else if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.loadGame) {
                        World savedWorld = (World)mainMenuScreen.gameScreenOnExitObject;
                        pushScreen((IGameScreen)new WargameScreen(savedWorld));
                        handled = true;
                    } else if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.openCreditsScreen) {
                        pushScreen((IGameScreen)new CreditsScreen());
                        handled = true;
                    } else if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.openMapEditor) {
                        BattleMapEditorScreen editor = new BattleMapEditorScreen();
                        if (BattleMapEditorScreen.canLoadFile())
                            editor.loadFromFile();
                        pushScreen((IGameScreen)editor);
                        editor.init();
                        handled = true;
                    } else if (mainMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.playEditorCreatedMap) {
                        BattleMapEditorScreen editorScreen = new BattleMapEditorScreen();
                        editorScreen.loadFromFile();
                        pushScreen((IGameScreen)editorScreen);
                        editorScreen.init();
                        int playerCount = ((editorScreen.getBattlefield()).grid.getPlayerSpawnPositions()).size;
                        int enemyCount = ((editorScreen.getBattlefield()).grid.getEnemySpawnPositions()).size;
                        World world = WorldBuilder.buildBase();
                        world.player.members.clear();
                        world.player.battleRoster.clear();
                        world.player.members = WorldBuilder.generateEntities(11, playerCount, "adv/", 0.7F, 0.0F);
                        world.player.battleRoster.addAll(world.player.members);
                        BattlefieldSettings settings = new BattlefieldSettings(world, world.player.battleRoster, world.player.members);
                        ArenaDefinition arenaDefinition = settings.arena;
                        arenaDefinition.zoneId = Zone.ZoneId.savanna;
                        arenaDefinition.tiles = BattleMapEditorScreen.loadSerializedTilesFromFileStatic();
                        settings.enemyEntities = WorldBuilder.generateEntities(11, enemyCount, "scavenger/", 0.8F, 0.0F);
                        BattleMapScreen battleMapScreen = new BattleMapScreen();
                        pushScreen((IGameScreen)battleMapScreen);
                        battleMapScreen.init(settings);
                        handled = true;
                    }
            }
            if (!handled) {
                WargameScreen wargameScreen = as(WargameScreen.class, getCurrentGameScreen());
                if (wargameScreen != null)
                    if (wargameScreen.gameScreenActionOnExit == GameScreenActionOnExit.openResumeMenu) {
                        pushScreen((IGameScreen)new MainMenuResumeScreen());
                        handled = true;
                    } else if (wargameScreen.gameScreenActionOnExit == GameScreenActionOnExit.openEntityManagement) {
                        WargameScreen.PartyScreenInput partyScreenInput = (WargameScreen.PartyScreenInput)cast.as(WargameScreen.PartyScreenInput.class, wargameScreen.getGameScreenOnExitObject());
                        if (partyScreenInput != null) {
                            pushScreen((IGameScreen)new PartyScreen(partyScreenInput.world, partyScreenInput.members, partyScreenInput.battleRoster, partyScreenInput.inventory));
                        } else {
                            pushScreen((IGameScreen)new PartyScreen(wargameScreen.world));
                        }
                        handled = true;
                    } else if (wargameScreen.gameScreenActionOnExit == GameScreenActionOnExit.openGameWonScreen) {
                        pushScreen((IGameScreen)new GameWonScreen());
                        handled = true;
                    } else if (wargameScreen.gameScreenActionOnExit == GameScreenActionOnExit.openGameOverScreen) {
                        log.info("Low trilium or all members dead or party leader dead -> game over!");
                        popScreen();
                        pushScreen((IGameScreen)new GameOverScreen(wargameScreen.world));
                        handled = true;
                    } else if (wargameScreen.gameScreenActionOnExit == GameScreenActionOnExit.startBattle) {
                        BattleMapScreen bms = new BattleMapScreen();
                        pushScreen((IGameScreen)bms);
                        bms.init(wargameScreen.world.battleRequest.battlefieldSettings);
                        handled = true;
                    }
            }
            if (!handled) {
                GameOverScreen gameOverScreen = as(GameOverScreen.class, getCurrentGameScreen());
                if (gameOverScreen != null) {
                    handled = true;
                    popScreen();
                    MenuBaseScreen mainMenuScreen = as(MenuBaseScreen.class, getCurrentGameScreen());
                    if (mainMenuScreen == null)
                        log.info("Warning: The current game screen should be Main Menu");
                }
            }
            if (!handled) {
                TrailerMenuScreen trailerMenuScreen = as(TrailerMenuScreen.class, getCurrentGameScreen());
                if (trailerMenuScreen != null) {
                    handled = true;
                    if (trailerMenuScreen.gameScreenActionOnExit == GameScreenActionOnExit.loadNamedScreen) {
                        IGameScreen gs;
                        try {
                            gs = (IGameScreen)ClassReflection.newInstance(ClassReflection.forName("kaleta.hex3.gameScreens." + trailerMenuScreen.gameScreenLoadNamedScreenOnExit));
                        } catch (Exception ex) {
                            throw new HexGameException("Could not create instace of BattleMapScreen by classname: kaleta.hex3.gameScreens. " + trailerMenuScreen.gameScreenLoadNamedScreenOnExit + ". Inner exception:" + ex.toString());
                        }
                        pushScreen(gs);
                        gs.init();
                    }
                }
            }
            if (!handled) {
                GameSetupScreen gameSetupScreen = as(GameSetupScreen.class, getCurrentGameScreen());
                if (gameSetupScreen != null)
                    if (gameSetupScreen.gameScreenActionOnExit == GameScreenActionOnExit.continueNewGame_show_intro) {
                        popScreen();
                        pushScreen((IGameScreen)new IntroScreen(gameSetupScreen.getStartSettings()));
                        handled = true;
                    } else if (gameSetupScreen.getGameScreenActionOnExit() == GameScreenActionOnExit.openUnitInfoScreen) {
                        pushScreen((IGameScreen)new UnitInfoScreen((HexEntity)gameSetupScreen.getGameScreenOnExitObject()));
                        handled = true;
                    }
            }
            if (!handled) {
                IntroScreen introScreen = as(IntroScreen.class, getCurrentGameScreen());
                if (introScreen != null)
                    if (introScreen.gameScreenActionOnExit == GameScreenActionOnExit.continueNewGame_start_campaign) {
                        popScreen();
                        pushScreen((IGameScreen)new WargameScreen(introScreen.getStartSettings()));
                        handled = true;
                    }
            }
            if (!handled) {
                GameScreen currentScreen = as(GameScreen.class, getCurrentGameScreen());
                if (currentScreen.getGameScreenActionOnExit() == GameScreenActionOnExit.exitToTitle) {
                    handled = true;
                    while (this.screenStack.size > 1)
                        popScreen();
                }
                if (currentScreen.getGameScreenActionOnExit() == GameScreenActionOnExit.openBattleHelp) {
                    pushScreen((IGameScreen)new BattleHelpScreen());
                    handled = true;
                } else if (currentScreen.getGameScreenActionOnExit() == GameScreenActionOnExit.openSettingsMenu) {
                    pushScreen((IGameScreen)new GlobalSettingsScreen());
                    handled = true;
                } else if (currentScreen.gameScreenActionOnExit == GameScreenActionOnExit.loadNamedScreen) {
                    IGameScreen gs;
                    handled = true;
                    try {
                        gs = (IGameScreen)ClassReflection.newInstance(
                                ClassReflection.forName("kaleta.hex3.gameScreens." + currentScreen.gameScreenLoadNamedScreenOnExit));
                    } catch (Exception ex) {
                        throw new HexGameException("Could not create instace of BattleMapScreen by classname: kaleta.hex3.gameScreens. " + currentScreen.gameScreenLoadNamedScreenOnExit + ". Inner exception:" + ex.toString());
                    }
                    pushScreen(gs);
                    gs.init();
                }
            }
            if (!handled)
                popScreen();
        } else if (gameScreenAction == GameScreenAction.exitGame) {
            Gdx.app.exit();
        }
    }
}