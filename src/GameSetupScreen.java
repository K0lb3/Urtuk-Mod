//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.SkeletonRenderer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import kaleta.hex3.GlobalGameProgress;
import kaleta.hex3.GlobalLocale;
import kaleta.hex3.battle.battleDifficulty.EasyDifficulty;
import kaleta.hex3.battle.battleDifficulty.GameDifficulty;
import kaleta.hex3.battle.battleDifficulty.HardDifficulty;
import kaleta.hex3.battle.battleDifficulty.HellDifficulty;
import kaleta.hex3.battle.battleDifficulty.NormalDifficulty;
import kaleta.hex3.battle.battleDifficulty.VeryHardDifficulty;
import kaleta.hex3.battle.entities.Gx.HexEntityAnimations;
import kaleta.hex3.battle.entities.Gx.SpineAnimation;
import kaleta.hex3.gameScreens.GameScreenAction;
import kaleta.hex3.gameScreens.GameScreenActionOnExit;
import kaleta.hex3.gameScreens.data.StartGameSettings;
import kaleta.hex3.gameScreens.uiWidgets.GameSetupPartyMember;
import kaleta.hex3.gameScreens.uilayout.IUIElement;
import kaleta.hex3.gameScreens.uilayout.ImageButton;
import kaleta.hex3.gameScreens.uilayout.LabelElement;
import kaleta.hex3.gameScreens.uilayout.TextAreaElement;
import kaleta.hex3.gameScreens.uilayout.UIContainer;
import kaleta.hex3.gameScreens.uilayout.UIElementBase;
import kaleta.hex3.gameScreens.uilayout.UIRoot;
import kaleta.hex3.gameScreens.uilayout.UIStackContainer;
import kaleta.hex3.globals.GlobalFonts;
import kaleta.hex3.pgc.content.RandomName;
import kaleta.hex3.sounds.ContextMusics.Context;
import kaleta.hex3.utils.Func;
import kaleta.hex3.utils.Func0;
import kaleta.hex3.utils.RandomInt;
import kaleta.hex3.utils.UIBuilder;
import kaleta.hex3.utils.query;
import kaleta.hex3.utils.rgb;
import kaleta.hex3.utils.screen;
import kaleta.hex3.utils.UIBuilder.ButtonBuilder;
import kaleta.hex3.utils.UIBuilder.TextboxBuilder;
import kaleta.hex3.world.customCampaign.adventure.AdventureCampaignMode;
import kaleta.hex3.world.data.WorldEntity;
import kaleta.hex3.world.data.WorldEntityCollection;
import kaleta.hex3.world.data.WorldEntityCustomizerFactory;
import kaleta.hex3.world.data.WorldItem;
import kaleta.hex3.world.data.WorldEntityCustomizerFactory.DefaultCustomizer;
import kaleta.hex3.world.data.classDefinitions.ClassDefinition;
import kaleta.hex3.world.data.classDefinitions.ClassDefinitionCollection;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AcidImmunity;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddAgilityFixedValue_II;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddAgilityFixedValue_III;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddConcentrationFixedValue_II;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddConcentrationFixedValue_III;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddConcentration_RelativeValue;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddMaxHP_II;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.AddMaxHP_III;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.BashImmunity;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.Birdy;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.BloodCurse;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.ClassTrait;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.FirstStrike;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.LightFoot;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.Masochist;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.NaturalResistance;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.NecroSkin;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnBlock_Counterstrike_II;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnCritical_ApplyBleeding;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnCritical_ApplyPoison;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnCritical_GainSpeed;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnCritical_ReduceSpeed;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnDeathwatch_SelfRegen;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnEachTurn_Retaliation;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnEachTurn_SuddenStrike;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnMaxFocus_ApplyToSelf;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnMaxFocus_GainCriticalStrike;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnNearbyKilled_GainDmgAndRegen;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.OnSelfDamage_GainBonusDamage;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.Rage;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.ShieldAllyPassive;
import kaleta.hex3.world.data.classDefinitions.triggers.traits.Veteran;
import kaleta.hex3.world.data.items.ItemType;
import kaleta.hex3.world.data.items.mutators.Mutator;
import kaleta.hex3.world.data.perks.AggressivePartyMember;
import kaleta.hex3.world.data.perks.Vampire;
import kaleta.hex3.world.data.perks.Werebeast;
import kaleta.hex3.world.rewards.common.helpers.SlottedItemElement;

public class GameSetupScreen extends kaleta.hex3.gameScreens.GameSetupScreen {
    UIRoot rootUI;
    UIContainer member1Cont;
    UIContainer member2Cont;
    UIContainer member3Cont;
    Color labelColor = rgb.get(255, 168, 0, 255.0F);
    private HashMap<ClassDefinition, SpineAnimation> animations = new HashMap();
    private static Array<ClassDefinition> availableClassDefinitions = ClassDefinitionCollection.getAll("adv/");
    private static Array<Integer> currentClassesForParty = query.create(new Integer[]{0, 0, 0});
    private static Array<String> names = query.create(new String[]{RandomName.randomHumanMale(), RandomName.randomHumanMale()});
    public GameSetupScreen.Party currentParty;
    protected HashMap<String, Boolean> itemsSelected;
    protected HashMap<String, Consumer<StartGameSettings>> itemsSelectedActions;
    protected StartGameSettings startGameSettings;
    private UIContainer itemsCont;

    public GameSetupScreen() {
        this.currentParty =  Party.standard;
        this.itemsSelected = new HashMap();
        this.itemsSelectedActions = new HashMap();
        this.startGameSettings = new StartGameSettings();
        this.clearColor = (new Color(Color.DARK_GRAY)).mul(0.45F);
        this.rootUI = new UIRoot();
        this.musicContext = Context.menu;
        WorldEntityCustomizerFactory.setCurrent(new DefaultCustomizer());
    }

    public StartGameSettings getStartSettings() {
        return this.startGameSettings;
    }

    public void onAssetsAsyncLoaded() {
        this.createUI();
    }

    public void loadAssets() {
        this.assets.load((String)null, "screen/gameSetup");
        this.assets.load((String)null, "ui");
    }

    private void createUI() {
        this.rootUI.removeAllChildren();
        final UIBuilder uib = new UIBuilder(this.assets, this.mouseNormal);
        UIContainer mainContainer = new UIContainer(1440.0F, 1080.0F);
        this.rootUI.add("center-x;center-y", mainContainer);
        LabelElement label = new LabelElement(this.mouseNormal, GlobalLocale.t_x(441, "CHOOSE YOUR PARTY"), GlobalFonts.lithosPro_30, this.labelColor);
        this.rootUI.add("center-x; top: 25", label);
        UIStackContainer partiesButtons = new UIStackContainer((float)(this.assets.get("button-setup").getRegionWidth() * 5), 200.0F);
        this.rootUI.add("center-x; top:70", partiesButtons);
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(442, "Default")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.standard;
                GameSetupScreen.this.resetCurrentPartyToDefaultParty();
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.standard;
            }
        }).setTooltip(GlobalLocale.t_x(7389, "Recommended party for players on their first run. ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        partiesButtons.addStacking(new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(1257, "Scavengers")) {
            public void onClick() {
                GameSetupScreen.this.currentParty = Party.scavangers;
                GameSetupScreen.this.resetCurrentParty("scavenger/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty == Party.scavangers;
            }
        });
        partiesButtons.addStacking(new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(1917, "Cultists")) {
            public void onClick() {
                GameSetupScreen.this.currentParty = Party.culstists;
                GameSetupScreen.this.resetCurrentParty("cultist/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty == Party.culstists;
            }
        });
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(7095, "Swampers")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.swampers;
                GameSetupScreen.this.resetCurrentParty("swamp");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 2;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.swampers;
            }
        }).setTooltip(GlobalLocale.t_x(7390, "Play as the #h{Swampers}! ~ This option unlocks when you've progressed to: zone 2! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(5374, "Forsaken")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.forsaken;
                GameSetupScreen.this.resetCurrentParty("forsaken/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 3;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.forsaken;
            }
        }).setTooltip(GlobalLocale.t_x(7391, "Play as the #h{Forsaken}! ~ This option unlocks when you've progressed to: zone 3! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(2031, "Vampires")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.vampires;
                GameSetupScreen.this.resetCurrentPartyToVampiresParty();
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 3;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.vampires;
            }
        }).setTooltip(GlobalLocale.t_x(7392, "Play as the #h{Vampires}! ~ Unlocks when you've made enough progress in the campaign: reach zone 3! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(2032, "Werebeasts")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.werebeasts;
                GameSetupScreen.this.resetCurrentParty("werebeast/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 3;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.werebeasts;
            }
        }).setTooltip(GlobalLocale.t_x(7393, "Play as the #h{Werebeasts}! ~ Unlocks when you've made enough progress in the campaign: reach zone 3! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), GlobalLocale.t_x(7128, "Beasts")) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.beasts;
                GameSetupScreen.this.resetCurrentParty("beast/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 4;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.beasts;
            }
        }).setTooltip(GlobalLocale.t_x(7394, "Play as the #h{Beasts}! ~ This option unlocks when you've progressed to: zone 4! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign."), this.assets));
        String revenants = GlobalLocale.t_x(2033, "???");
        String revenantTooltip = GlobalLocale.t_x(7395, "Unknown at this time. ~ Unlocks when you've made enough progress in the campaign: win the game on at least #h{Adventure} difficulty! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign.");
        if ((Integer)GlobalGameProgress.current.gameWonMaxDifficulty.value >= NormalDifficulty.rating) {
            revenants = ClassDefinitionCollection.getRaceFriendlyName("revenant/");
            revenantTooltip = GlobalLocale.t_x(7396, "Play as the #h{%1%}! ~ ~ When you have won the game with this faction, extra 3x mutators will unlock for a new campaign.", new Object[]{revenants});
        }

        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), revenants) {
            public void onClick() {
                GameSetupScreen.this.currentParty =  Party.revenants;
                GameSetupScreen.this.resetCurrentParty("revenant/");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.gameWonMaxDifficulty.value < NormalDifficulty.rating;
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty ==  Party.revenants;
            }
        }).setTooltip(revenantTooltip, this.assets));
        partiesButtons.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), "Grand Boss") {
                    public void onClick() {
                        GameSetupScreen.this.currentParty =  Party.boss;
                        GameSetupScreen.this.resetCurrentParty("special/grand-boss/");
                        GameSetupScreen.this.handlePartySelectionChanged();
                    }

                    public boolean isDisabled() {
                        return (Integer)GlobalGameProgress.current.zoneMaxTier.value < 4;
                    }

                    public boolean isHighlighted() {
                        return GameSetupScreen.this.currentParty ==  Party.boss;
                    }
                })
        );
        partiesButtons.addStacking(new ImageButton(this.mouseNormal, this.assets.get("button-setup"), "All") {
            public void onClick() {
                GameSetupScreen.this.currentParty = Party.all;
                GameSetupScreen.this.resetCurrentParty("");
                GameSetupScreen.this.handlePartySelectionChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.currentParty == Party.all;
            }
        });
        this.resetCurrentPartyToDefaultParty();
        Func0<WorldEntity> entity1 = () -> {
            return (WorldEntity)this.startGameSettings.members.get(0);
        };
        this.member1Cont = GameSetupPartyMember.create(0, entity1, this.mouseNormal, uib, this);
        mainContainer.add("left:290;top: 120", this.member1Cont);
        Func0<WorldEntity> entity2 = () -> {
            return (WorldEntity)this.startGameSettings.members.get(1);
        };
        this.member2Cont = GameSetupPartyMember.create(1, entity2, this.mouseNormal, uib, this);
        mainContainer.add("center-x:0;top: 120", this.member2Cont);
        Func0<WorldEntity> entity3 = () -> {
            return (WorldEntity)this.startGameSettings.members.get(2);
        };
        this.member3Cont = GameSetupPartyMember.create(2, entity3, this.mouseNormal, uib, this);
        mainContainer.add("right:290;top: 120", this.member3Cont);
        mainContainer.add("center-x; bottom:13", new ImageButton(this.mouseNormal, this.assets.get("button-startGame")) {
            public void onClick() {
                UIContainer popup = uib.container_small();
                popup.add("center-x; top: 30", new LabelElement(GameSetupScreen.this.mouseStatic, GlobalLocale.t_x(3019, "Ironman?"), GlobalFonts.fashr26));
                popup.add("center-x; top: 90", new TextAreaElement(GameSetupScreen.this.mouseStatic, GlobalLocale.t_x(3020, "In #h{Ironman} mode your progress will be saved automatically. No manual saving is allowed."), 425.0F));
                popup.add("bottom:50; left:80", uib.button_small(ButtonBuilder.onClick(() -> {
                    uib.openTextboxPopup(GameSetupScreen.this.rootUI, TextboxBuilder.onConfirm((txt) -> {
                        GameSetupScreen.this.startGameSettings.isIronman = true;
                        GameSetupScreen.this.startGameSettings.ironmanSavename = txt;
                        GameSetupScreen.this.gameScreenAction = GameScreenAction.exit;
                        GameSetupScreen.this.gameScreenActionOnExit = GameScreenActionOnExit.continueNewGame_show_intro;
                        GameSetupScreen.this.onStartingGame();
                    }).label(GlobalLocale.t_x(3021, "Ironman save name")).maxCharacters(10));
                }).label(GlobalLocale.t_x(3022, "Yeah!"))));
                popup.add("bottom:50; right:80", uib.button_small(ButtonBuilder.onClick(() -> {
                    GameSetupScreen.this.gameScreenAction = GameScreenAction.exit;
                    GameSetupScreen.this.gameScreenActionOnExit = GameScreenActionOnExit.continueNewGame_show_intro;
                    GameSetupScreen.this.onStartingGame();
                }).label(GlobalLocale.t_x(3023, "No way!"))));
                GameSetupScreen.this.rootUI.addPopup(popup);
            }

            public boolean isDisabled() {
                return GameSetupScreen.this.startGameSettings.members.size == 0;
            }
        });
        mainContainer.add("left:100; bottom:0", new ImageButton(this.mouseNormal, this.assets.get("back-button")) {
            public void onClick() {
                GameSetupScreen.this.gameScreenAction = GameScreenAction.exit;
            }
        });
        float shiftY = 30.0F;
        this.itemsCont = new UIContainer(850.0F, 300.0F);
        this.rootUI.add("center-x; top: " + (460.0F + shiftY), this.itemsCont);
        Array<WorldEntity> potentialMembers = new Array();
        GameSetupScreen.Party[] var13 =  Party.values();
        int var14 = var13.length;

        for(int var15 = 0; var15 < var14; ++var15) {
            GameSetupScreen.Party p = var13[var15];
            this.currentParty = p;
            this.handlePartySelectionChanged();
            potentialMembers.addAll(this.startGameSettings.members);
        }

        UIStackContainer diffCont = new UIStackContainer((float)(5 * this.assets.get("button-setup").getRegionWidth()), 300.0F);
        this.rootUI.add("center-x; top: " + (820.0F + shiftY), diffCont);
        label = new LabelElement(this.mouseNormal, GlobalLocale.t_x(445, "CHOOSE DIFFICULTY"), GlobalFonts.lithosPro_30, this.labelColor);
        diffCont.add("center-x; top: 20", label);
        diffCont.nextRow();
        diffCont.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), (new EasyDifficulty()).toString()) {
            public void onClick() {
                GameSetupScreen.this.startGameSettings.difficulty = -1;
                GameDifficulty.setCurrent(GameSetupScreen.this.startGameSettings.difficulty);
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.startGameSettings.difficulty == -1;
            }
        }).setTooltip(GlobalLocale.t_x(447, "For less experienced tactical RPG players, though still quite challenging!"), this.assets));
        diffCont.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), (new NormalDifficulty()).toString()) {
            public void onClick() {
                GameSetupScreen.this.startGameSettings.difficulty = 0;
                GameDifficulty.setCurrent(GameSetupScreen.this.startGameSettings.difficulty);
                GameSetupScreen.this.onDifficultyChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.startGameSettings.difficulty == 0;
            }
        }).setTooltip(GlobalLocale.t_x(449, "For experienced tactical RPG fans, and those who've already played the game. Very challenging!"), this.assets));
        diffCont.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), (new HardDifficulty()).toString()) {
            public void onClick() {
                GameSetupScreen.this.startGameSettings.difficulty = 1;
                GameDifficulty.setCurrent(GameSetupScreen.this.startGameSettings.difficulty);
                GameSetupScreen.this.onDifficultyChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.startGameSettings.difficulty == 1;
            }
        }).setTooltip(GlobalLocale.t_x(7244, "Adventure difficulty is an easy afternoon of monster slaying by comparison. You'll need to pay attention, use your resources wisely and outwit your enemies."), this.assets));
        diffCont.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), (new VeryHardDifficulty()).toString()) {
            public void onClick() {
                GameSetupScreen.this.startGameSettings.difficulty = 2;
                GameDifficulty.setCurrent(GameSetupScreen.this.startGameSettings.difficulty);
                GameSetupScreen.this.onDifficultyChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.startGameSettings.difficulty == 2;
            }
        }).setTooltip(GlobalLocale.t_x(451, "You know this game's every mechanic and are a highly skilled tactical thinker. Hard as hell! If you win the game at this difficulty, call me up for a beer! ~ ~ #h-gray{Healing reduced by 40%} ~ #h-gray{Focus gain reduced by 30%} ~ #h-gray{Medicine cost, Recovery time and Enemy damage increased}"), this.assets));
        diffCont.addStacking((new ImageButton(this.mouseNormal, this.assets.get("button-setup"), (new HellDifficulty()).toString()) {
            public void onClick() {
                GameSetupScreen.this.startGameSettings.difficulty = 3;
                GameDifficulty.setCurrent(GameSetupScreen.this.startGameSettings.difficulty);
                GameSetupScreen.this.onDifficultyChanged();
            }

            public boolean isHighlighted() {
                return GameSetupScreen.this.startGameSettings.difficulty == 3;
            }

            public boolean isDisabled() {
                return (Integer)GlobalGameProgress.current.gameWonMaxDifficulty.value < 2;
            }
        }).setTooltip(GlobalLocale.t_x(7230, "This setting is for suicidal players only. ~ If your goal is to hate this game, please choose this difficulty. ~ Your starting characters have their absorption slots increased by 1. ~ ~ #h-gray{Unlocks when you’ve completed the game on Epic difficulty}"), this.assets));
        this.currentParty =  Party.standard;
        this.handlePartySelectionChanged();
    }

    private void setAvailableClassesTo_adventurers() {
        availableClassDefinitions = ClassDefinitionCollection.getAll("adv/");
        availableClassDefinitions.add(ClassDefinitionCollection.get("special/villager-weaponsmith"));
        availableClassDefinitions.add(ClassDefinitionCollection.get("special/villager-armoursmith"));
    }

    private void setAvailableClassesTo_vampires() {
        availableClassDefinitions.clear();
        availableClassDefinitions.add(ClassDefinitionCollection.get("vampire/origin"));
        availableClassDefinitions.add(ClassDefinitionCollection.get("vampire/lord"));
        availableClassDefinitions.add(ClassDefinitionCollection.get("vampire/mace"));
        availableClassDefinitions.add(ClassDefinitionCollection.get("vampire/ghoul"));
    }

    private void resetCurrentPartyToDefaultParty() {
        this.setAvailableClassesTo_adventurers();
        Func<String, Integer> idx = (cd_id) -> {
            return availableClassDefinitions.indexOf(query.single(availableClassDefinitions, (cd) -> {
                return cd.id.equals(cd_id);
            }), true);
        };
        currentClassesForParty.set(0, idx.get("adv/priest"));
        currentClassesForParty.set(1, idx.get("adv/guardian"));
        currentClassesForParty.set(2, idx.get("adv/berserker"));
        this.startGameSettings.members.clear();
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(0), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(1), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(2), 1));
        ((WorldEntity)this.startGameSettings.members.get(1)).name = (String)names.get(0);
        ((WorldEntity)this.startGameSettings.members.get(2)).name = (String)names.get(1);
    }

    private void resetCurrentPartyToVampiresParty() {
        this.setAvailableClassesTo_vampires();
        Func<String, Integer> idx = (cd_id) -> {
            return availableClassDefinitions.indexOf(query.single(availableClassDefinitions, (cd) -> {
                return cd.id.equals(cd_id);
            }), true);
        };
        currentClassesForParty.set(0, idx.get("vampire/origin"));
        currentClassesForParty.set(1, idx.get("vampire/origin"));
        currentClassesForParty.set(2, idx.get("vampire/origin"));
        this.startGameSettings.members.clear();
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(0), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(1), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(2), 1));
        ((WorldEntity)this.startGameSettings.members.get(1)).name = (String)names.get(0);
        ((WorldEntity)this.startGameSettings.members.get(2)).name = (String)names.get(1);
    }

    private void resetCurrentParty(String filter) {
        availableClassDefinitions = ClassDefinitionCollection.getAll(filter);
        currentClassesForParty.set(0, 0);
        currentClassesForParty.set(1, 0);
        currentClassesForParty.set(2, 0);
        this.nextCustomClass(0, RandomInt.Get(0, availableClassDefinitions.size - 1));
        this.nextCustomClass(1, RandomInt.Get(0, availableClassDefinitions.size - 1));
        this.nextCustomClass(2, RandomInt.Get(0, availableClassDefinitions.size - 1));
        this.startGameSettings.members.clear();
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(0), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(1), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get(2), 1));
        ((WorldEntity)this.startGameSettings.members.get(1)).name = (String)names.get(0);
        ((WorldEntity)this.startGameSettings.members.get(2)).name = (String)names.get(1);
    }

    public void nextCustomClass(int memberPosition, int delta) {
        int curr = (Integer)currentClassesForParty.get(memberPosition) + delta;
        if (curr < 0) {
            curr = availableClassDefinitions.size - 1;
        }
        curr %= availableClassDefinitions.size;
        currentClassesForParty.set(memberPosition, curr);
        this.handleMemberSelectionChanged(memberPosition);
    }

    private void handlePartySelectionChanged() {
        this.startGameSettings.members.clear();
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get((Integer)currentClassesForParty.get(0)), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get((Integer)currentClassesForParty.get(1)), 1));
        this.startGameSettings.members.add(WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get((Integer)currentClassesForParty.get(2)), 1));
        ((WorldEntity)this.startGameSettings.members.get(1)).name = (String)names.get(0);
        ((WorldEntity)this.startGameSettings.members.get(2)).name = (String)names.get(1);
        WorldEntity leader = (WorldEntity)this.startGameSettings.members.first();
        leader.name = "Urtuk";
        this.updateItemsToChoose();
    }

    private void handleMemberSelectionChanged(int memberPosition) {
        this.startGameSettings.members.removeIndex(memberPosition);
        this.startGameSettings.members.insert(memberPosition, WorldEntityCollection.get((ClassDefinition)availableClassDefinitions.get((Integer)currentClassesForParty.get(memberPosition)), 1));
        ((WorldEntity)this.startGameSettings.members.get(1)).name = (String)names.get(0);
        ((WorldEntity)this.startGameSettings.members.get(2)).name = (String)names.get(1);
        WorldEntity leader = (WorldEntity)this.startGameSettings.members.first();
        leader.name = "Urtuk";
        this.updateItemsToChoose();
    }

    private void onDifficultyChanged() {
    }

    private void updateItemsToChoose() {
        this.itemsSelected.clear();
        this.itemsSelectedActions.clear();
        this.itemsCont.removeAllChildren();
        this.itemsCont.add("center-x; top: 0", new LabelElement(this.mouseNormal, GlobalLocale.t_x(7397, "CHOOSE THREE ITEMS"), GlobalFonts.lithosPro_30, this.labelColor));
        UIStackContainer itemsCollection = new UIStackContainer(1100.0F, 200.0F);
        this.itemsCont.add("center-x; top: 40", itemsCollection);
        this.addItemSlot(GlobalLocale.t_x(453, "Extra Medicine (3x)"), itemsCollection, (w) -> {
            w.extraMedicine += 3;
        });
        Array<Mutator> mutators = query.create(new Mutator[]{new Mutator(new OnMaxFocus_ApplyToSelf()), new Mutator(new OnMaxFocus_GainCriticalStrike()), new Mutator(new LightFoot()), new Mutator(new LightFoot()), new Mutator(new LightFoot()), new Mutator(new AddAgilityFixedValue_II()), new Mutator(new AddConcentrationFixedValue_II()), new Mutator(new AddMaxHP_II()), new Mutator(new OnCritical_ApplyBleeding()), new Mutator(new OnCritical_ApplyPoison()), new Mutator(new OnCritical_GainSpeed()), new Mutator(new OnCritical_ReduceSpeed())});
        query.forEach(mutators, (m) -> {
            m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
            this.addItemSlot(m, itemsCollection, (w) -> {
                w.items.add(m);
            });
        });
        Array mutators1;
        if ((Boolean)GlobalGameProgress.current.humanFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new AddConcentration_RelativeValue()), new Mutator(new Veteran()), new Mutator(new Birdy())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.werebeastsFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new BashImmunity()), new Mutator(new AddMaxHP_III()), (new Mutator(new OnNearbyKilled_GainDmgAndRegen())).setMutatorQualityLevel(7)});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.vampiresFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new BloodCurse()), new Mutator(new Masochist()), new Mutator(new Rage())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.beastsFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new OnEachTurn_SuddenStrike()), (new Mutator(new OnDeathwatch_SelfRegen())).setMutatorQualityLevel(7), new Mutator(new AddAgilityFixedValue_III())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.forsakenFactionWon.value) {
            mutators1 = query.create(new Mutator[]{(new Mutator(new OnSelfDamage_GainBonusDamage())).setMutatorQualityLevel(7), new Mutator(new AddConcentrationFixedValue_III()), new Mutator(new OnEachTurn_Retaliation())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.swampersFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new NaturalResistance()), (new Mutator(new ShieldAllyPassive())).setMutatorQualityLevel(7), new Mutator(new OnBlock_Counterstrike_II())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }

        if ((Boolean)GlobalGameProgress.current.revenantsFactionWon.value) {
            mutators1 = query.create(new Mutator[]{new Mutator(new NecroSkin()), new Mutator(new FirstStrike()), new Mutator(new AcidImmunity())});
            query.forEach(mutators1, (m1) -> {
                Mutator m = (Mutator)m1;
                if (m.getTrait().getQualityLevel() == 1) {
                    m.setMutatorQualityLevel(ClassTrait.QualityLevelThreshold);
                }

                this.addItemSlot(m, itemsCollection, (w) -> {
                    w.items.add(m);
                });
            });
        }
    }

    private void onStartingGame() {
        GameDifficulty.setCurrent(this.startGameSettings.difficulty);
        WorldEntity leader = (WorldEntity)this.startGameSettings.members.first();
        query.forEach(this.startGameSettings.members, (m) -> {
            this.startGameSettings.members.removeValue(m, true);
            WorldEntity clone = WorldEntityCollection.get(m.id, 1);
            clone.classDefinition.traits.clear();
            query.forEach(m.classDefinition.traits, (t) -> {
                clone.classDefinition.traits.add(t.clone());
            });
            this.startGameSettings.members.add(clone);
            if (m == leader) {
                clone.name = leader.name;
            }

            clone.classDefinition.maxMutatorAbsorptions = GameDifficulty.current.getModifiedMaxAbsorptionsSlots_for_StarterClasses(m.classDefinition.maxMutatorAbsorptions);
        });
        this.startGameSettings.campaignMode = new AdventureCampaignMode();
        Iterator var2 = this.itemsSelectedActions.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, Consumer<StartGameSettings>> kvp = (Entry)var2.next();
            if (this.itemsSelected.containsKey(kvp.getKey()) && (Boolean)this.itemsSelected.get(kvp.getKey())) {
                ((Consumer)kvp.getValue()).accept(this.startGameSettings);
            }
        }

        query.forEach(this.startGameSettings.members, (member) -> {
            query.forEach(member.classDefinition.equipables, (eqItemType) -> {
                if (!query.contains(member.classDefinition.traits, (t) -> {
                    return t.isMultiGearWielder();
                }) && eqItemType != ItemType.mutator) {
                    WorldItem item = (WorldItem)query.single(this.startGameSettings.items, (i) -> {
                        return eqItemType == i.getItemType();
                    });
                    if (item != null) {
                        WorldItem equippedItem = (WorldItem)query.single(member.upgrades.items.all, (i) -> {
                            return i.getItemType() == item.getItemType();
                        });
                        if (equippedItem != null) {
                            member.upgrades.items.all.removeValue(equippedItem, true);
                        }

                        member.addItem(item.clone());
                        this.startGameSettings.items.removeValue(item, true);
                    }
                }

            });
            if (this.currentParty ==  Party.vampires) {
                member.addPerk(new Vampire(member));
            }

            if (this.currentParty ==  Party.werebeasts) {
                member.addPerk(new Werebeast(member, member));
                ((Werebeast)query.singleOfType(member.getPerks(), Werebeast.class)).setAsOriginalWerebeast();
            }

            if (this.currentParty ==  Party.revenants) {
                member.addPerk(new AggressivePartyMember(member));
            }

            member.addStrongVsTrait(false);
        });
        if (this.currentParty ==  Party.vampires) {
            this.startGameSettings.extraBlood = 100;
        }

        if (this.currentParty ==  Party.werebeasts) {
            this.startGameSettings.extraFlesh = 100;
        }

        if (this.currentParty ==  Party.beasts) {
            this.startGameSettings.extraFlesh = 100;
        }

    }

    private void addItemSlot(Object obj, UIStackContainer itemsCollection, Consumer<StartGameSettings> action) {
        float iconScale = 0.75F;
        UIContainer container = new UIContainer(95.0F * iconScale, 160.0F * iconScale);
        itemsCollection.addStacking(container);
        final String itemId;
        if (obj instanceof WorldItem) {
            WorldItem item = (WorldItem)obj;
            itemId = item.id + RandomInt.Get(0, 10000);
            this.itemsSelectedActions.put(itemId, action);
            UIElementBase slot = (new SlottedItemElement(item, this.assets, this.mouseNormal) {
                public void onClick() {
                    GameSetupScreen.this.handleItemSelectionClicked(itemId);
                }
            }).setScale(iconScale);
            container.add("center-x;center-y", slot);
            slot.setTooltip(item.getDescription(), this.assets);
        } else {
            SlottedItemElement slot;
            if (obj instanceof String && ((String)obj).startsWith("mutator-teaser")) {
                itemId = (String)obj;
                this.itemsSelectedActions.put(itemId, action);
                slot = (new SlottedItemElement(this.assets.get("inventory/" + itemId), this.assets, this.mouseNormal)).setScale(iconScale);
                container.add("center-x;center-y", slot);
                slot.setTooltip(GlobalLocale.t_x(7398, "#h{Locked} ~ ~ Unlocks when you've won the campaign."), this.assets);
            } else {
                itemId = obj.toString();
                this.itemsSelectedActions.put(itemId, action);
                slot = (new SlottedItemElement(this.assets.get("inventory/medicine"), this.assets, this.mouseNormal) {
                    public void onClick() {
                        GameSetupScreen.this.handleItemSelectionClicked(itemId);
                    }
                }).setScale(iconScale);
                container.add("center-x;center-y", slot);
                slot.setTooltip(obj.toString(), this.assets);
            }
        }

        UIContainer itemSelected = (UIContainer)(new UIContainer(this.assets.get("icon-checked"), this.mouseNormal) {
            public boolean isDrawn() {
                return GameSetupScreen.this.itemsSelected.containsKey(itemId) && (Boolean)GameSetupScreen.this.itemsSelected.get(itemId);
            }
        }).enableClickBubblingDown();
        container.add("bottom:10; right:10", itemSelected);
    }

    private boolean canSelectItem(String id) {
        if (id.startsWith("mutator-teaser")) {
            return false;
        } else {
            return query.count(query.toArray(this.itemsSelected.values()), (i) -> {
                return i;
            }) < 3;
        }
    }

    private void handleItemSelectionClicked(String id) {
        if (!this.itemsSelected.containsKey(id)) {
            this.itemsSelected.put(id, false);
        }

        if ((Boolean)this.itemsSelected.get(id)) {
            this.itemsSelected.put(id, false);
        } else if (!(Boolean)this.itemsSelected.get(id) && this.canSelectItem(id)) {
            this.itemsSelected.put(id, true);
        }

    }

    public GameScreenAction tick(float deltaTime) {
        super.tick(deltaTime);
        this.rootUI.tick();
        return this.gameScreenAction;
    }

    public void draw(PolygonSpriteBatch batch, SkeletonRenderer skeletonRenderer, float deltaTime) {
        if (this.assetsAsyncLoaded) {
            TextureRegion txt = this.assets.get("choose party container");
            int h = txt.getRegionHeight();
            batch.draw(txt, (float)screen.centerX() - (float)txt.getRegionWidth() / 2.0F, (float)(screen.top(5) - h));
            txt = this.assets.get("choose item container");
            batch.draw(txt, (float)screen.centerX() - (float)txt.getRegionWidth() / 2.0F, (float)(screen.top(5) - h - txt.getRegionHeight()));
            IUIElement cont = this.member1Cont;
            WorldEntity entity = (WorldEntity)this.startGameSettings.members.get(0);
            Vector2 pos = new Vector2(cont.getPositionX() + cont.getWidth() / 2.0F, cont.getPositionY());
            SpineAnimation animation = this.getAnimation(entity.classDefinition);
            animation.draw(deltaTime, batch, skeletonRenderer, pos.x, pos.y + 75.0F, false);
            cont = this.member2Cont;
            entity = (WorldEntity)this.startGameSettings.members.get(1);
            pos = new Vector2(cont.getPositionX() + cont.getWidth() / 2.0F, cont.getPositionY());
            animation = this.getAnimation(entity.classDefinition);
            animation.draw(deltaTime, batch, skeletonRenderer, pos.x, pos.y + 75.0F, false);
            cont = this.member3Cont;
            entity = (WorldEntity)this.startGameSettings.members.get(2);
            pos = new Vector2(cont.getPositionX() + cont.getWidth() / 2.0F, cont.getPositionY());
            animation = this.getAnimation(entity.classDefinition);
            animation.draw(deltaTime, batch, skeletonRenderer, pos.x, pos.y + 75.0F, false);
            this.rootUI.draw(batch, skeletonRenderer);
        }
    }

    private SpineAnimation getAnimation(ClassDefinition def) {
        SpineAnimation animation = null;
        if (!this.animations.containsKey(def)) {
            animation = HexEntityAnimations.createSpineAnimation(def.spine.skeletonName, def.spine.skinName, def.spine.shouldFlipSkeletonToRight, false);
            animation.setTint(def.spine.tint);
            animation.state.setTimeScale(0.5F);
            this.animations.put(def, animation);
            animation.setAnimation("idle", true);
        }

        return (SpineAnimation)this.animations.get(def);
    }

    public static enum Party {
        standard,
        all,
        boss,
        culstists,
        scavangers,
        vampires,
        werebeasts,
        revenants,
        forsaken,
        swampers,
        beasts;
        private Party() {
        }
    }
}
