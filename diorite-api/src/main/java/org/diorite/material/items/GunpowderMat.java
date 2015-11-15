/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.material.items;

import java.util.Map;

import org.diorite.material.ItemMaterialData;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

/**
 * Class representing 'Gunpowder' item material in minecraft. <br>
 * ID of material: 289 <br>
 * String ID of material: minecraft:gunpowder <br>
 * Max item stack size: 64
 */
@SuppressWarnings("JavaDoc")
public class GunpowderMat extends ItemMaterialData
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final GunpowderMat GUNPOWDER = new GunpowderMat();

    private static final Map<String, GunpowderMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<GunpowderMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    @SuppressWarnings("MagicNumber")
    public GunpowderMat()
    {
        super("GUNPOWDER", 289, "minecraft:gunpowder", "GUNPOWDER", (short) 0x00);
    }

    public GunpowderMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type)
    {
        super(enumName, id, minecraftId, typeName, type);
    }

    public GunpowderMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type);
    }

    @Override
    public GunpowderMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public GunpowderMat getType(final String type)
    {
        return getByEnumName(type);
    }

    /**
     * Returns one of Gunpowder sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of Gunpowder or null
     */
    public static GunpowderMat getByID(final int id)
    {
        return byID.get((short) id);
    }

    /**
     * Returns one of Gunpowder sub-type based on name (selected by diorite team), may return null
     * If item contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Gunpowder or null
     */
    public static GunpowderMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final GunpowderMat element)
    {
        allItems.incrementAndGet();
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public GunpowderMat[] types()
    {
        return GunpowderMat.gunpowderTypes();
    }

    /**
     * @return array that contains all sub-types of this item.
     */
    public static GunpowderMat[] gunpowderTypes()
    {
        return byID.values(new GunpowderMat[byID.size()]);
    }

    static
    {
        GunpowderMat.register(GUNPOWDER);
    }
}
