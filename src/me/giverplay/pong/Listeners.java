package me.giverplay.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listeners implements KeyListener
{
  private Game game;

  public Key right, left, enter;

  public Listeners(Game game)
  {
    this.game = game;
    game.addKeyListener(this);

    right = new Key();
    left = new Key();
    enter = new Key();
  }

  @Override
  public void keyTyped(KeyEvent e)
  {

  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    release(e.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    release(e.getKeyCode(), false);
  }

  public void release(int keycode, boolean pressed)
  {
    switch (keycode)
    {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        right.release(pressed);
        break;

      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        left.release(pressed);
        break;

      case KeyEvent.VK_ENTER:
        enter.release(pressed);
        break;

      default:
        break;
    }
  }

  public class Key
  {
    public boolean pressed = false;

    public void release(boolean pressed)
    {
      this.pressed = pressed;
    }
  }
}
