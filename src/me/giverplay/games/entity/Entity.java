package me.giverplay.games.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity
{
  protected static final Random random = new Random();

  private BufferedImage sprite;
  private String name;

  private double x, y, speed, dx, dy;
  private int maskX, maskY, height, width, maskWidth, maskHeight;

  public Entity(double x, double y, BufferedImage defaultSprite)
  {
    this(x, y, 16, 16, defaultSprite);
  }

  public Entity(double x, double y, int width, int height, BufferedImage defaultSprite)
  {
    this.x = x;
    this.y = y;
    this.speed = 2D;
    this.width = width;
    this.height = height;
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

  public void reflectDx()
  {
    dx *= -1;
  }

  public void reflectDy()
  {
    dy *= -1;
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

  public double nextX()
  {
    return x + speed;
  }

  public double previousX()
  {
    return x - speed;
  }

  public double nextY()
  {
    return y + speed;
  }

  public double previousY()
  {
    return y - speed;
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
    return new Rectangle(getXInt() + maskX, getYInt() + maskY, width - maskWidth, height - maskHeight);
  }

  public Rectangle getNextCollisionBox()
  {
    return new Rectangle((int) nextX() + maskX, (int) nextY() + maskY, width - maskWidth, height - maskHeight);
  }

  public Rectangle getPreviousCollisionBox()
  {
    return new Rectangle((int) previousX() + maskX, (int) previousY() + maskY, width - maskWidth, height - maskHeight);
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

  public boolean checkPossibility(int percent)
  {
    return checkPossibility(percent, 100);
  }

  public boolean checkPossibility(int min, int max)
  {
    return random.nextInt(max) < min;
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
