/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETException;

/**
 * Colourizes grey-scale icons, such as the Item.gif and CreateChild.gif icons generated with EMF.Edit item providers.
 * Such icons can include one or two different grey scales to be colourized.
 * @since 2.2.0
 */
public class GIFEmitter
{
  protected String inputFile;

  protected static final int tableOffset1 = 49;
  protected static final int tableOffset2 = 25;

  public GIFEmitter(String inputFile)
  {
    this.inputFile = inputFile;
  }

  protected int code(String code)
  {
    int result = 0;
    for (int i = 0; i < code.length(); ++ i)
    {
      result += code.charAt(i) - 32;
    }
    return result;
  }

  public byte[] generateGIF(String key1, String key2)
  {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try
    {
      byte [] content = new byte [5000];
      int result = getContents(content, inputFile);

      // generateColor();
      GIFEmitter.ColorInformation info1 = ColorInformation.getColor(code(key1));
      GIFEmitter.ColorInformation info2 = key2 == null ? null : ColorInformation.getColor(code(key2));


      for (int j = 0; j < result; ++j)
      {
        if (j == tableOffset1 || j == tableOffset1 + 3 || j == tableOffset1 + 6 || j == tableOffset1 + 9)
        {
          int index = (j - tableOffset1) / 3;
          if (!info1.rainbow || info1.which == index - 1)
          {
            content[j] = info1.scale(info1.red, info1.factor[index]);
          }
        }
        else if (j == tableOffset1 + 1 || j == tableOffset1 + 4 || j == tableOffset1 + 7 || j == tableOffset1 + 10)
        {
          int index = (j - tableOffset1 - 1) / 3;
          if (!info1.rainbow || info1.which == index - 1)
          {
            content[j] = info1.scale(info1.green, info1.factor[index]);
          }
        }
        else if (j == tableOffset1 + 2 || j == tableOffset1 + 5 || j == tableOffset1 + 8 || j == tableOffset1 + 11)
        {
          int index = (j - tableOffset1 - 2) / 3;
          if (!info1.rainbow || info1.which == index - 1)
          {
            content[j] = info1.scale(info1.blue, info1.factor[index]);
          }
        }

        if (info2 != null)
        {
          if (j == tableOffset2 || j == tableOffset2 + 3 || j == tableOffset2 + 6 || j == tableOffset2 + 9)
          {
            int index = (j - tableOffset2) / 3;
            if (!info2.rainbow || info2.which == index - 1)
            {
              content[j] = info2.scale(info2.red, info2.factor[index]);
            }
          }
          else if (j == tableOffset2 + 1 || j == tableOffset2 + 4 || j == tableOffset2 + 7 || j == tableOffset2 + 10)
          {
            int index = (j - tableOffset2 - 1) / 3;
            if (!info2.rainbow || info2.which == index - 1)
            {
              content[j] = info2.scale(info2.green, info2.factor[index]);
            }
          }
          else if (j == tableOffset2 + 2 || j == tableOffset2 + 5 || j == tableOffset2 + 8 || j == tableOffset2 + 11)
          {
            int index = (j - tableOffset2 - 2) / 3;
            if (!info2.rainbow || info2.which == index - 1)
            {
              content[j] = info2.scale(info2.blue, info2.factor[index]);
            }
          }
        }
      }

      DataOutputStream writer = new DataOutputStream(outputStream);
      writer.write(content, 0, result);
      writer.close();
    }
    catch (Exception exception)
    {
      CodeGenPlugin.INSTANCE.log(exception);
    }

    return outputStream.toByteArray();
  }

  protected int getContents(byte [] content, String gifFile) throws JETException, IOException
  {
    DataInputStream reader = new DataInputStream(JETCompiler.openStream(gifFile));
    int result = reader.read(content, 0, content.length);
    reader.close();
    return result;
  }

  protected static class ColorInformation
  {
    public static GIFEmitter.ColorInformation getColor(int index)
    {
      index = Math.abs(index) % 61;
      while (entries.size() <= index)
      {
        instance.generateColor();

        GIFEmitter.ColorInformation entry = new ColorInformation();
        entry.red = instance.red;
        entry.green = instance.green;
        entry.blue = instance.blue;
        entry.which = instance.which;
        entry.factor = new double [] { instance.factor[0], instance.factor[1], instance.factor[2], instance.factor[3] };
        entry.rainbow = instance.rainbow;
        entries.add(entry);
        instance.fixFactor();
      }
      return entries.get(index);
    }

    protected static GIFEmitter.ColorInformation instance = new ColorInformation();

    protected static List<GIFEmitter.ColorInformation> entries = new ArrayList<GIFEmitter.ColorInformation>(1000);

    public int red = 192;
    public int green = 64;
    public int blue = 64;

    public int which  = 2;
    public int change  = 64;

    public double [] factor = { 0.35, 0.1, -0.1, -0.3 };
    public boolean rainbow;

    public byte scale(int value, double factor)
    {
      if (factor > 0.0)
      {
        return (byte)(value + (255 - value) * factor);
      }
      else
      {
        return (byte)(value + value * factor);
      }
    }

    protected void generateColor()
    {
      switch (which)
      {
        case 0:
        {
          red += change;
          if (red <= 64)
          {
            which = 1;
            change = -change;
          }
          else if (red >= 192)
          {
            which = 1;
            change = -change;
          }
          break;
        }
        case 1:
        {
          green += change;
          if (green >= 192)
          {
            which = 2;
            change = -change;
          }
          else if (green <= 64)
          {
            which = 2;
            change = -change;
          }
          break;
        }
        case 2:
        {
          blue += change;
          if (blue >= 192)
          {
            which = 0;
            change = -change;
          }
          else if (blue <=64)
          {
            which = 0;
            change = -change;
          }
          break;
        }
      }
    }

    protected void fixFactor()
    {
      if (red == 192 && green == 64 && blue == 64)
      {
        for (int j = 0; j < factor.length; ++j)
        {
          factor[j] += 0.3;
        }
        if (factor[0] >= 1.0)
        {
          rainbow = true;
          for (int j = 0; j < factor.length; ++j)
          {
            factor[j] -= 0.8;
          }
        }
      }
    }
  }
}