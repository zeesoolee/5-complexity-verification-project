interface A
{
  public void f();
};

class B implements A
{
  public void f()
  {
    assert false;
  }
};

class C implements A
{
  public void f(){ }
};

class SatVirtual4
{
  public static void main(String[] args)
  {
    A a = new C();
    a.f();
  }
}

