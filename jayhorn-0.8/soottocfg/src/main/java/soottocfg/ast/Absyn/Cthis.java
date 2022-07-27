package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class Cthis extends SpecExp {
  public final SpecName specname_;
  public Cthis(SpecName p1) { specname_ = p1; }

  public <R,A> R accept(soottocfg.ast.Absyn.SpecExp.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.Cthis) {
      soottocfg.ast.Absyn.Cthis x = (soottocfg.ast.Absyn.Cthis)o;
      return this.specname_.equals(x.specname_);
    }
    return false;
  }

  public int hashCode() {
    return this.specname_.hashCode();
  }


}
