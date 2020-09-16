package me.giverplay.games.game;

import javax.swing.event.MouseInputListener;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class KeyBind implements KeyListener, MouseInputListener, MouseWheelListener
{
  public final Key right = new Key();
  public final Key left = new Key();
  public final Key up = new Key();
  public final Key down = new Key();
  public final Key enter = new Key();
  public final Key shift = new Key();
  public final Key esc = new Key();
  public final Key control = new Key();
  public final Key tab = new Key();

  public KeyBind(Canvas canvas)
  {
    canvas.addKeyListener(this);
    canvas.addMouseListener(this);
    canvas.addMouseWheelListener(this);
    canvas.addMouseMotionListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e)
  {

  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    toggle(e.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    toggle(e.getKeyCode(), false);
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {

  }

  @Override
  public void mousePressed(MouseEvent e)
  {

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }

  @Override
  public void mouseDragged(MouseEvent e)
  {

  }

  @Override
  public void mouseMoved(MouseEvent e)
  {

  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e)
  {

  }

  private void toggle(int key, boolean state)
  {
    switch (key)
    {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        right.toggle(state);
        break;

      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        left.toggle(state);
        break;

      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        up.toggle(state);
        break;

      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        down.toggle(state);
        break;

      case KeyEvent.VK_CONTROL:
        control.toggle(state);
        break;

      case KeyEvent.VK_ENTER:
        enter.toggle(state);
        break;

      case KeyEvent.VK_SHIFT:
        shift.toggle(state);
        break;

      case KeyEvent.VK_ESCAPE:
        esc.toggle(state);
        break;

      case KeyEvent.VK_TAB:
        tab.toggle(state);
        break;
    }
  }

  public static class Key
  {
    private boolean pressed = false;

    public void toggle(boolean pressed)
    {
      this.pressed = pressed;
    }

    public boolean isPressed()
    {
      return this.pressed;
    }
  }
}
