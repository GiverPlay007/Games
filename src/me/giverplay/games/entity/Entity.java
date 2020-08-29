package me.giverplay.games.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity
{
  BufferedImage sprite;
  private String name;

  private double x, y, speed, dx, dy;
  private int maskX, maskY, height, width, maskWidth, maskHeight;

  public Entity(double x, double y, BufferedImage defaultSprite)
  {
    this.x = x;
    this.y = y;
    this.speed = 2D;
    this.width = 16;
    this.height = 16;
    this.sprite = defaultSprite;
  }

  public double getSpeed()
  {
    return speed;
  }

  public void setX(double x)
  {
    this.x = x;
  }

  public double getX()
  {
    return x;
  }

  public void setMaskHeight(int maskHeight)
  {
    this.maskHeight = maskHeight;
  }

  public void setMaskWidth(int maskWidth)
  {
    this.maskWidth = maskWidth;
  }

  public void setY(double y)
  {
    this.y = y;
  }

  public void setSpeed(double speed)
  {
    this.speed = speed;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public double getDx()
  {
    return dx;
  }

  public void setDx(double dx)
  {
    this.dx = dx;
  }

  public double getDy()
  {
    return dy;
  }

  public void setDy(double dy)
  {
    this.dy = dy;
  }

  public double getY()
  {
    return y;
  }

  public void moveY(double dis)
  {
    this.y += dis;
  }

  public void moveX(double dis)
  {
    this.x += dis;
  }

  public int getHeight()
  {
    return height;
  }

  public int getWidth()
  {
    return width;
  }

  public int getMaskX()
  {
    return maskX;
  }

  public int getMaskY()
  {
    return maskY;
  }

  public void setMaskX(int maskX)
  {
    this.maskX = maskX;
  }

  public void setMaskY(int maskY)
  {
    this.maskY = maskY;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public int getMaskHeight()
  {
    return maskHeight;
  }

  public int getMaskWidth()
  {
    return maskWidth;
  }

  public void setHeight(int height)
  {
    this.height = height;
  }

  public int getXInt()
  {
    return (int) this.x;
  }

  public int getYInt()
  {
    return (int) this.y;
  }

  public Rectangle getCollisionBox()
  {
    return new Rectangle(getXInt() + maskX, getYInt() + maskY, getXInt() + width - maskWidth, getYInt() + height - maskHeight);
  }

  public boolean isCollliding(Entity entity)
  {
    return getCollisionBox().intersects(entity.getCollisionBox());
  }

  public BufferedImage getSprite()
  {
    return sprite;
  }

  public void setSprite(BufferedImage sprite)
  {
    this.sprite = sprite;
  }

  public void tick()
  {
    if(dx != 0.0D)
    {
      moveX(dx * speed);
    }

    if(dy != 0.0D)
    {
      moveY(dy * speed);
    }
  }

  public void render(Graphics g)
  {
    if(g != null && sprite != null)
    {
      g.drawImage(sprite, getXInt(), getYInt(), getWidth(), getHeight(), null);
    }
  }
}
