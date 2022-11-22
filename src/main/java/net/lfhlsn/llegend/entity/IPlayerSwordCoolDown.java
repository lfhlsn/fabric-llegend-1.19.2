package net.lfhlsn.llegend.entity;

public interface IPlayerSwordCoolDown {
    int dragonSwordMaxCoolDown_1 = 2000;
    int dragonSwordMaxCoolDown_2 = 10000;
    int dragonSwordMaxCoolDown_3 = 60000;
    int endSwordMaxCoolDown_1 = 3000;
    int endSwordMaxCoolDown_2 = 4000;
    int unknownSwordMaxCoolDown_2 = 8000;
    int frozenSwordMaxCoolDown_1 = 6000;
    int creeperSwordMaxCoolDown_1 = 5000;
    int creeperSwordMaxCoolDown_2 = 15000;
    int witherSwordMaxCoolDown_1 = 3000;
    int witherSwordMaxCoolDown_2 = 10000;
    int witherSwordMaxCoolDown_3 = 80000;
    int getDragonSwordCoolDown_1();

    void setDragonSwordCoolDown_1(int dragonSwordCoolDown_1);

    int getDragonSwordCoolDown_2();

    void setDragonSwordCoolDown_2(int dragonSwordCoolDown_2);

    int getEndSwordCoolDown_1();

    void setEndSwordCoolDown_1(int endSwordCoolDown_1);

    int getFrozenSwordCoolDown_1();

    void setFrozenSwordCoolDown_1(int frozenSwordCoolDown_1);

    int getDragonSwordCoolDown_3();

    void setDragonSwordCoolDown_3(int dragonSwordCoolDown_3);

    int getEndSwordCoolDown_2();

    void setEndSwordCoolDown_2(int endSwordCoolDown_2);

    int getUnknownSwordCoolDown_2();

    void setUnknownSwordCoolDown_2(int unknownSwordCoolDown_2);

    int getCreeperSwordCoolDown_1();

    void setCreeperSwordCoolDown_1(int creeperSwordCoolDown_1);

    int getCreeperSwordCoolDown_2();

    void setCreeperSwordCoolDown_2(int creeperSwordCoolDown_2);

    int getWitherSwordCoolDown_1();

    void setWitherSwordCoolDown_1(int witherSwordCoolDown_1);

    int getWitherSwordCoolDown_2();

    void setWitherSwordCoolDown_2(int witherSwordCoolDown_2);

    int getWitherSwordCoolDown_3();

    void setWitherSwordCoolDown_3(int witherSwordCoolDown_3);
}
