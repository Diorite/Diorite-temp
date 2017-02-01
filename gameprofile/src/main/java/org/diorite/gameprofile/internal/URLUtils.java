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

package org.diorite.gameprofile.internal;

import javax.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public final class URLUtils
{
    private URLUtils()
    {
    }

    public static String encodeUTF8(@Nullable String str)
    {
        if (str == null)
        {
            return "";
        }
        try
        {
            return URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        { // should never be thrown
            throw new RuntimeException(e);
        }
    }

    public static String createQueryElement(String key, @Nullable String value)
    {
        String result = encodeUTF8(key);
        if (value != null)
        {
            result += "=" + encodeUTF8(value);
        }
        return result;
    }

    public static String buildQuery(@Nullable Map<String, Object> query)
    {
        if ((query == null) || query.isEmpty())
        {
            return "";
        }
        return query.entrySet().stream().map(entry -> createQueryElement(entry.getKey(), (entry.getValue() == null) ? null : entry.getValue().toString()))
                    .reduce((s1, s2) -> s1 + "&" + s2).orElse("");
    }

    public static URL createURL(String address)
    {
        try
        {
            return new URL(address);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException("Can't create URL object for: " + address, e);
        }
    }

    public static URL createURL(URL url, String query)
    {
        try
        {
            if ((url.getQuery() != null) && (! url.getQuery().isEmpty()))
            {
                return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "&" + query);
            }
            return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "?" + query);
        }
        catch (MalformedURLException ex)
        {
            throw new RuntimeException("Can't create URL object for query: " + query + ", URL: " + url, ex);
        }
    }
}