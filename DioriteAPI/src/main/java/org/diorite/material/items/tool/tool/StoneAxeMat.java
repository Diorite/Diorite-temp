package org.diorite.material.items.tool.tool;

import java.util.Map;

import org.diorite.material.ToolMaterial;
import org.diorite.material.ToolType;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;
import org.diorite.utils.lazy.LazyValue;
import org.diorite.utils.math.DioriteMathUtils;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

@SuppressWarnings("ClassHasNoToStringMethod")
public class StoneAxeMat extends AxeMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final StoneAxeMat STONE_AXE = new StoneAxeMat();

    private static final Map<String, StoneAxeMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<StoneAxeMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    protected final LazyValue<StoneAxeMat> next = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() + 1) : null);
    protected final LazyValue<StoneAxeMat> prev = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() - 1) : null);

    @SuppressWarnings("MagicNumber")
    protected StoneAxeMat()
    {
        super("STONE_AXE", 275, "minecraft:stone_Axe", "NEW", (short) 0, ToolMaterial.STONE);
    }

    protected StoneAxeMat(final int durability)
    {
        super(STONE_AXE.name(), STONE_AXE.getId(), STONE_AXE.getMinecraftId(), Integer.toString(durability), (short) durability, ToolMaterial.STONE);
    }

    protected StoneAxeMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type, final ToolMaterial toolMaterial, final ToolType toolType)
    {
        super(enumName, id, minecraftId, typeName, type, toolMaterial, toolType);
    }

    protected StoneAxeMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type, final ToolMaterial toolMaterial, final ToolType toolType)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, toolMaterial, toolType);
    }

    @Override
    public StoneAxeMat[] types()
    {
        return new StoneAxeMat[]{STONE_AXE};
    }

    @Override
    public StoneAxeMat getType(final String type)
    {
        return getByEnumName(type);
    }

    @Override
    public StoneAxeMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public StoneAxeMat increaseDurability()
    {
        return this.next.get();
    }

    @Override
    public StoneAxeMat decreaseDurability()
    {
        return this.prev.get();
    }

    @Override
    public StoneAxeMat setDurability(final int durability)
    {
        return this.getType(durability);
    }

    /**
     * Returns one of StoneAxe sub-type based on sub-id.
     *
     * @param id sub-type id
     *
     * @return sub-type of StoneAxe.
     */
    public static StoneAxeMat getByID(final int id)
    {
        StoneAxeMat mat = byID.get((short) id);
        if (mat == null)
        {
            mat = new StoneAxeMat(id);
            if ((id > 0) && (id < STONE_AXE.getBaseDurability()))
            {
                StoneAxeMat.register(mat);
            }
        }
        return mat;
    }

    /**
     * Returns one of StoneAxe sub-type based on durability.
     *
     * @param id durability of type.
     *
     * @return sub-type of StoneAxe.
     */
    public static StoneAxeMat getByDurability(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of StoneAxe-type based on name (selected by diorite team).
     * If block contains only one type, sub-name of it will be this same as name of material.<br>
     * Returns null if name can't be parsed to int and it isn't "NEW" one.
     *
     * @param name name of sub-type
     *
     * @return sub-type of StoneAxe.
     */
    public static StoneAxeMat getByEnumName(final String name)
    {
        final StoneAxeMat mat = byName.get(name);
        if (mat == null)
        {
            final Integer idType = DioriteMathUtils.asInt(name);
            if (idType == null)
            {
                return null;
            }
            return getByID(idType);
        }
        return mat;
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final StoneAxeMat element)
    {
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static StoneAxeMat[] stoneAxeTypes()
    {
        return new StoneAxeMat[]{STONE_AXE};
    }

    static
    {
        StoneAxeMat.register(STONE_AXE);
    }
}