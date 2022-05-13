package fi.dy.masa.litematica.render.schematic;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import fi.dy.masa.litematica.render.schematic.ChunkRendererSchematicVbo.OverlayRenderType;

public class BufferBuilderCache
{
    private final Map<RenderLayer, CompatBuffer> blockBufferBuilders = new HashMap<>();
    private CompatBuffer[] overlayBufferBuilders;

    public BufferBuilderCache()
    {
        for (RenderLayer layer : RenderLayer.getBlockLayers())
        {
            this.blockBufferBuilders.put(layer, new CompatBuffer(layer.getExpectedBufferSize()));
        }

        this.overlayBufferBuilders = new CompatBuffer[OverlayRenderType.values().length];

        for (int i = 0; i < this.overlayBufferBuilders.length; ++i)
        {
            this.overlayBufferBuilders[i] = new CompatBuffer(262144);
        }
    }

    public CompatBuffer getBlockBufferByLayer(RenderLayer layer)
    {
        return this.blockBufferBuilders.get(layer);
    }

    public CompatBuffer getOverlayBuffer(OverlayRenderType type)
    {
        return this.overlayBufferBuilders[type.ordinal()];
    }

    public void clear()
    {
        this.blockBufferBuilders.values().forEach(CompatBuffer::reset);

        for (CompatBuffer buffer : this.overlayBufferBuilders)
        {
            buffer.reset();
        }
    }
}
