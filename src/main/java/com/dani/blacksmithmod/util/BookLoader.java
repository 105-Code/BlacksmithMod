package com.dani.blacksmithmod.util;

import net.minecraft.resources.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Unit;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;

public class BookLoader implements IReloadableResourceManager {

    @Override
    public CompletableFuture<Unit> reloadResourcesAndThen(Executor p_219536_1_, Executor p_219536_2_, List<IResourcePack> p_219536_3_, CompletableFuture<Unit> p_219536_4_) {
        return null;
    }

    @Override
    public IAsyncReloader reloadResources(Executor backgroundExecutor, Executor gameExecutor, CompletableFuture<Unit> waitingFor, List<IResourcePack> p_219537_4_) {
        return null;
    }

    @Override
    public void addReloadListener(IFutureReloadListener p_219534_1_) {

    }

    @Override
    public Set<String> getResourceNamespaces() {
        return null;
    }

    @Override
    public IResource getResource(ResourceLocation resourceLocationIn) throws IOException {
        return null;
    }

    @Override
    public boolean hasResource(ResourceLocation p_219533_1_) {
        return false;
    }

    @Override
    public List<IResource> getAllResources(ResourceLocation resourceLocationIn) throws IOException {
        return null;
    }

    @Override
    public Collection<ResourceLocation> getAllResourceLocations(String pathIn, Predicate<String> filter) {
        return null;
    }
}
