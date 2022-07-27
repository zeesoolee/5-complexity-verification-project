package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class Eint extends Constant {
  public final Integer integer_;
  public Eint(Integer p1) { integer_ = p1; }

  public <R,A> R accept(soottocfg.ast.Absyn.Constant.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.Eint) {
      soottocfg.ast.Absyn.Eint x = (soottocfg.ast.Absyn.Eint)o;
      return this.integer_.equals(x.integer_);
    }
    return false;
  }

  public int hashCode() {
    return this.integer_.hashCode();
  }


}
