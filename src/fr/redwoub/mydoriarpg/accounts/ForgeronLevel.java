package fr.redwoub.mydoriarpg.accounts;

import java.util.Arrays;

public enum ForgeronLevel {
    LVL1(0, 1),
    LVL2(200, 2),
    LVL3(400, 3),
    LVL4(600, 4),
    LVL5(800, 5),
    LVL6(1200, 6),
    LVL7(1600, 7),
    LVL8(2000, 8),
    LVL9(2400, 9),
    LVL10(2800, 10),
    LVL11(3400, 11),
    LVL12(4000, 12),
    LVL13(4600, 13),
    LVL14(5200, 14),
    LVL15(5800, 15),
    LVL16(6600, 16),
    LVL17(7400, 17),
    LVL18(8200, 18),
    LVL19(9000, 19),
    LVL20(9800, 20),
    LVL21(10800, 21),
    LVL22(11800, 22),
    LVL23(12800, 23),
    LVL24(13800, 24),
    LVL25(14800, 25),
    LVL26(16300, 26),
    LVL27(17800, 27),
    LVL28(19300, 28),
    LVL29(20800, 29),
    LVL30(22300, 30),
    LVL31(24300, 31),
    LVL32(26300, 32),
    LVL33(28300, 33),
    LVL34(30300, 34),
    LVL35(32300, 35),
    LVL36(34800, 36),
    LVL37(37300, 37),
    LVL38(39800, 38),
    LVL39(42300, 39),
    LVL40(45000, 40),
    LVL41(48000, 41),
    LVL42(51000, 42),
    LVL43(54000, 43),
    LVL44(57000, 44),
    LVL45(60000, 45),
    LVL46(64000, 46),
    LVL47(68000, 47),
    LVL48(72000, 48),
    LVL49(76000, 49),
    LVL50(80000, 50); //5000xp tt les 5, a refaire tout


    private int needingxp;
    private int lvl;
    private static final int maxLvl = 300;

    ForgeronLevel(int needingxp, int lvl){
        this.needingxp = needingxp;
        this.lvl = lvl;
    }

    private int getLvl(){
        return lvl;
    }

    public int getNeedingxp() {
        return needingxp;
    }

    public static int needingxp(int lvl){
        return Arrays.stream(values()).filter(l -> l.getLvl() == lvl).findFirst().orElse(LVL2).getNeedingxp();
    }

    public static int getMaxLevel(){
        return maxLvl;
    }
}
