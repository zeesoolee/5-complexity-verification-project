package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class Sel extends Stm {
  public final SelectionStm selectionstm_;
  public Sel(SelectionStm p1) { selectionstm_ = p1; }

  public <R,A> R accept(soottocfg.ast.Absyn.Stm.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.Sel) {
      soottocfg.ast.Absyn.Sel x = (soottocfg.ast.Absyn.Sel)o;
      return this.selectionstm_.equals(x.selectionstm_);
    }
    return false;
  }

  public int hashCode() {
    return this.selectionstm_.hashCode();
  }


}
