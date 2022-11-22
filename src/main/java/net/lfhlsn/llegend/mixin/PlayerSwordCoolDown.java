package net.lfhlsn.llegend.mixin;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerSwordCoolDown implements IPlayerSwordCoolDown {
    private int dragonSwordCoolDown_1 = 0;
    private int dragonSwordCoolDown_2 = 0;
    private int dragonSwordCoolDown_3 = 0;
    private int endSwordCoolDown_1 = 0;
    private int endSwordCoolDown_2 = 0;
    private int unknownSwordCoolDown_2 =0;
    private int frozenSwordCoolDown_1 =0;
    private int creeperSwordCoolDown_1 =0;
    private int creeperSwordCoolDown_2 =0;
    private int witherSwordCoolDown_1 =0;
    private int witherSwordCoolDown_2 =0;
    private int witherSwordCoolDown_3 =0;

    @Inject(method = "tick", at = @At("TAIL"))
    protected void injectTick(CallbackInfo ci){
        if(dragonSwordCoolDown_1 >0) dragonSwordCoolDown_1--;
        if(dragonSwordCoolDown_2 >0) dragonSwordCoolDown_2--;
        if(dragonSwordCoolDown_3 >0) dragonSwordCoolDown_3--;
        if(endSwordCoolDown_1 >0) endSwordCoolDown_1--;
        if(endSwordCoolDown_2 >0) endSwordCoolDown_2--;
        if(unknownSwordCoolDown_2 >0) unknownSwordCoolDown_2--;
        if(frozenSwordCoolDown_1 >0) frozenSwordCoolDown_1--;
        if(creeperSwordCoolDown_1 >0) creeperSwordCoolDown_1--;
        if(creeperSwordCoolDown_2 >0) creeperSwordCoolDown_2--;
        if(witherSwordCoolDown_1 >0) witherSwordCoolDown_1--;
        if(witherSwordCoolDown_2 >0) witherSwordCoolDown_2--;
        if(witherSwordCoolDown_3 >0) witherSwordCoolDown_3--;
    }

    @Override
    public int getDragonSwordCoolDown_1() {
        return dragonSwordCoolDown_1;
    }

    @Override
    public void setDragonSwordCoolDown_1(int dragonSwordCoolDown_1) {
        this.dragonSwordCoolDown_1 = dragonSwordCoolDown_1;
    }

    @Override
    public int getDragonSwordCoolDown_2() {
        return dragonSwordCoolDown_2;
    }

    @Override
    public void setDragonSwordCoolDown_2(int dragonSwordCoolDown_2) {
        this.dragonSwordCoolDown_2 = dragonSwordCoolDown_2;
    }

    @Override
    public int getEndSwordCoolDown_1() {
        return endSwordCoolDown_1;
    }

    @Override
    public void setEndSwordCoolDown_1(int endSwordCoolDown_1) {
        this.endSwordCoolDown_1 = endSwordCoolDown_1;
    }

    @Override
    public int getFrozenSwordCoolDown_1() {
        return frozenSwordCoolDown_1;
    }

    @Override
    public void setFrozenSwordCoolDown_1(int frozenSwordCoolDown_1) {
        this.frozenSwordCoolDown_1 = frozenSwordCoolDown_1;
    }

    @Override
    public int getDragonSwordCoolDown_3() {
        return dragonSwordCoolDown_3;
    }

    @Override
    public void setDragonSwordCoolDown_3(int dragonSwordCoolDown_3) {
        this.dragonSwordCoolDown_3 = dragonSwordCoolDown_3;
    }

    @Override
    public int getEndSwordCoolDown_2() {
        return endSwordCoolDown_2;
    }

    @Override
    public void setEndSwordCoolDown_2(int endSwordCoolDown_2) {
        this.endSwordCoolDown_2 = endSwordCoolDown_2;
    }

    @Override
    public int getUnknownSwordCoolDown_2() {
        return unknownSwordCoolDown_2;
    }

    @Override
    public void setUnknownSwordCoolDown_2(int unknownSwordCoolDown_2) {
        this.unknownSwordCoolDown_2 = unknownSwordCoolDown_2;
    }

    @Override
    public int getCreeperSwordCoolDown_1() {
        return creeperSwordCoolDown_1;
    }

    @Override
    public void setCreeperSwordCoolDown_1(int creeperSwordCoolDown_1) {
        this.creeperSwordCoolDown_1 = creeperSwordCoolDown_1;
    }

    @Override
    public int getCreeperSwordCoolDown_2() {
        return creeperSwordCoolDown_2;
    }

    @Override
    public void setCreeperSwordCoolDown_2(int creeperSwordCoolDown_2) {
        this.creeperSwordCoolDown_2 = creeperSwordCoolDown_2;
    }

    @Override
    public int getWitherSwordCoolDown_1() {
        return witherSwordCoolDown_1;
    }

    @Override
    public void setWitherSwordCoolDown_1(int witherSwordCoolDown_1) {
        this.witherSwordCoolDown_1 = witherSwordCoolDown_1;
    }

    @Override
    public int getWitherSwordCoolDown_2() {
        return witherSwordCoolDown_2;
    }

    @Override
    public void setWitherSwordCoolDown_2(int witherSwordCoolDown_2) {
        this.witherSwordCoolDown_2 = witherSwordCoolDown_2;
    }

    @Override
    public int getWitherSwordCoolDown_3() {
        return witherSwordCoolDown_3;
    }

    @Override
    public void setWitherSwordCoolDown_3(int witherSwordCoolDown_3) {
        this.witherSwordCoolDown_3 = witherSwordCoolDown_3;
    }
}
