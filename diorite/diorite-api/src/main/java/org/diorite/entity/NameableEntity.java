/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017. Diorite (by Bartłomiej Mazur (aka GotoFinal))
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

package org.diorite.entity;

import javax.annotation.Nullable;

/**
 * Represent entity that can have custom name.
 */
public interface NameableEntity
{
    /**
     * Returns custom name of entity.
     *
     * @return custom name of entity.
     */
    @Nullable
    String getCustomName();

    /**
     * Sets a custom name on a mob or block. This name will be used in death messages and can be sent to the client as a nameplate over the entity.
     *
     * @param name
     *         new name, or null to disable.
     */
    void setCustomName(@Nullable String name);

    /**
     * Returns true if custom name should be visible. <br>
     * Note that players always need to display name.
     *
     * @return true if custom name should be visible.
     */
    boolean isCustomNameVisible();

    /**
     * Set if custom name should be visible.
     *
     * @param visibility
     *         if custom name should be visible.
     */
    void setCustomNameVisible(boolean visibility);
}