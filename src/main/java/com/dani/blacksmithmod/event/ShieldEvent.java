package com.dani.blacksmithmod.event;

import com.dani.blacksmithmod.items.ShieldBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShieldEvent {

    @SubscribeEvent
    public static void attackEvent(LivingAttackEvent ev) {
        if (ev.getEntityLiving() instanceof PlayerEntity) {
            final int damage = (int) ev.getAmount();
            final PlayerEntity player = (PlayerEntity)ev.getEntityLiving();
            if(!player.getActiveItemStack().isEmpty()){
                final ItemStack activeStack = player.getActiveItemStack();
                if (damage >= 3.0f && !activeStack.isEmpty() && activeStack.getItem() instanceof ShieldBase) {
                    activeStack.damageItem(damage,ev.getEntityLiving(),(p_220039_0_) -> {
                        p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                    });
                }
            }
        }
    }
}
