package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_2;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Random;

public class EndSwordItem extends SwordItem implements SwordWithSkill_1, SwordWithSkill_2 {
    public final Random random=new Random();
    public EndSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private boolean teleportTo(LivingEntity entity,double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);
        while (mutable.getY() > entity.world.getBottomY() && !entity.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }
        BlockState blockState = entity.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (!bl || bl2) {
            return false;
        }
        Vec3d vec3d = entity.getPos();
        boolean bl3 = entity.teleport(x, y, z, true);
        if (bl3) {
            entity.world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
            if (!entity.isSilent()) {
                entity.world.playSound(null, entity.prevX, entity.prevY, entity.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, entity.getSoundCategory(), 1.0f, 1.0f);
                entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
        return bl3;
    }

    private boolean teleportRandomly(LivingEntity entity) {
        if (entity.world.isClient() || !entity.isAlive()) {
            return false;
        }
        double d = entity.getX() + (this.random.nextDouble() - 0.5) * 64.0;
        double e = entity.getY() + (double)(this.random.nextInt(64) - 32);
        double f = entity.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
        return this.teleportTo(entity, d, e, f);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker.isTouchingWaterOrRain()) stack.damage(3, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        else stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        World world=attacker.getWorld();
        if(random.nextInt(5)==0&&target.isAlive())
        {
            if(!world.isClient) {
                while (!teleportRandomly(target)){}
            }
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity.isTouchingWaterOrRain()&&random.nextInt(30)==0)
        {
            if(selected) stack.damage(3, (LivingEntity) entity, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            else stack.damage(1, (LivingEntity) entity, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getEndSwordCoolDown_1()==0){
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getItemCooldownManager().set(Items.ENDER_PEARL, 20);
            if (!world.isClient) {
                itemStack.damage(20,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, player);
                enderPearlEntity.setItem(Items.ENDER_PEARL.getDefaultStack());
                enderPearlEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(enderPearlEntity);
                ((IPlayerSwordCoolDown) player).setEndSwordCoolDown_1(IPlayerSwordCoolDown.endSwordMaxCoolDown_1);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getEndSwordCoolDown_1())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_2(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getEndSwordCoolDown_2()==0){
            if(!world.isClient){
                itemStack.damage(100,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                while (!teleportRandomly(player)){}
                ((IPlayerSwordCoolDown) player).setEndSwordCoolDown_2(IPlayerSwordCoolDown.endSwordMaxCoolDown_2);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getEndSwordCoolDown_2())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
