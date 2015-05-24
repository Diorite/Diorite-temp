package org.diorite.impl.world.io.mca;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.Main;
import org.diorite.impl.world.WorldImpl;
import org.diorite.impl.world.chunk.ChunkImpl;
import org.diorite.impl.world.chunk.ChunkManagerImpl;
import org.diorite.impl.world.io.ChunkIO;
import org.diorite.impl.world.io.WorldFile;
import org.diorite.nbt.NbtTagCompound;
import org.diorite.world.chunk.ChunkPos;

public class McaWorldFile extends WorldFile<WorldImpl, ChunkManagerImpl, ChunkIO, ChunkImpl>
{
    public McaWorldFile(final File worldDir, final WorldImpl world, final ChunkIO io)
    {
        super(worldDir, world, world.getChunkManager(), io);
    }

    @Override
    public ChunkImpl loadChunk(final ChunkPos pos)
    {
        return this.loadChunk(pos.getX(), pos.getZ());
    }

    @Override
    public ChunkImpl loadChunk(final int x, final int z)
    {
        try
        {
            final NbtTagCompound chunk = this.io.getChunkData(this.worldDir, x, z);
            if (chunk == null)
            {
                return null;
            }
            return ChunkImpl.loadFromNBT(this.world, chunk);
        } catch (final RuntimeException e)
        {
            System.err.println("[WorldFile] Error on chunk (" + x + ", " + z + ") loading: " + e.getMessage() + ", " + e.toString());
            if (Main.isEnabledDebug())
            {
                e.printStackTrace();
            }
            return new ChunkImpl(new ChunkPos(x, z, this.world));
        }
    }

    @Override
    public void saveChunk(final ChunkImpl chunk)
    {
        if (chunk == null)
        {
            return;
        }
        this.io.saveChunkData(this.worldDir, chunk.getX(), chunk.getZ(), chunk.writeTo(new NbtTagCompound("Level")));
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("worldDir", this.worldDir).append("world", this.world).toString();
    }
}
