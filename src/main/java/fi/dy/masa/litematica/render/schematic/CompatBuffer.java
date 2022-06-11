package fi.dy.masa.litematica.render.schematic;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat;
import org.jetbrains.annotations.Nullable;

public class CompatBuffer extends BufferBuilder {


    @Nullable
    public BuiltBuffer lastRenderBuildBuffer;

    public boolean first = true;

    public CompatBuffer(int initialCapacity) {
        super(initialCapacity);
    }

    public void begin(VertexFormat.DrawMode drawMode, VertexFormat format) {
        if (lastRenderBuildBuffer == null) {
            if (!first) {
                this.end();
            } else {
                first = false;
            }
        } else {
            lastRenderBuildBuffer = null;
        }
        super.begin(drawMode, format);
    }

    @Override
    public BuiltBuffer end() {
        lastRenderBuildBuffer = super.end();
        return lastRenderBuildBuffer;
    }
}
