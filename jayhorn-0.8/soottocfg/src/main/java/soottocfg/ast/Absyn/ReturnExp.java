package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class ReturnExp extends JumpStm {
  public final ListCommaExpList listcommaexplist_;
  public ReturnExp(ListCommaExpList p1) { listcommaexplist_ = p1; }

  public <R,A> R accept(soottocfg.ast.Absyn.JumpStm.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.ReturnExp) {
      soottocfg.ast.Absyn.ReturnExp x = (soottocfg.ast.Absyn.ReturnExp)o;
      return this.listcommaexplist_.equals(x.listcommaexplist_);
    }
    return false;
  }

  public int hashCode() {
    return this.listcommaexplist_.hashCode();
  }


}
