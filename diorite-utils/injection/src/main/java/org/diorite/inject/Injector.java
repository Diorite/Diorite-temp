/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Diorite (by Bartłomiej Mazur (aka GotoFinal))
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

package org.diorite.inject;

import javax.annotation.Nullable;

public final class Injector
{
    private Injector()
    {
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nullable
    @Deprecated
    public static <T> T injectField(Object $this, int typeId, int memberId, boolean performNullCheck)
    {
        return (T) ((InjectionController) Injection.getController()).getInjectedField($this, typeId, memberId, performNullCheck);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nullable
    @Deprecated
    public static <T> T injectMethod(Object $this, int typeId, int memberId, int argumentId, boolean performNullCheck)
    {
        return (T) ((InjectionController) Injection.getController()).getInjectedMethod($this, typeId, memberId, argumentId, performNullCheck);
    }
}
