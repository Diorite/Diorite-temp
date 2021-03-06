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

package org.diorite.config.serialization;

import java.util.function.BiConsumer;
import java.util.function.Function;

class SimpleSerializer<T> implements Serializer<T>
{
    private final Class<? super T>                 type;
    private final Function<DeserializationData, T> deserializer;
    private final BiConsumer<T, SerializationData> serializer;

    SimpleSerializer(Class<? super T> type, Function<DeserializationData, T> deserializer, BiConsumer<T, SerializationData> serializer)
    {
        this.type = type;
        this.deserializer = deserializer;
        this.serializer = serializer;
    }

    @Override
    public Class<? super T> getType()
    {
        return this.type;
    }

    @Override
    public Function<DeserializationData, T> getDeserializerFunction()
    {
        return this.deserializer;
    }

    @Override
    public BiConsumer<T, SerializationData> getSerializerFunction()
    {
        return this.serializer;
    }

    @Override
    public void serialize(T object, SerializationData data)
    {
        this.serializer.accept(object, data);
    }

    @Override
    public T deserialize(DeserializationData data)
    {
        return this.deserializer.apply(data);
    }
}
