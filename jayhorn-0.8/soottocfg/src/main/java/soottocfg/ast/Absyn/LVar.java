package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class LVar extends LVarStatement {
  public final Type type_;
  public final ListVarDecl listvardecl_;
  public LVar(Type p1, ListVarDecl p2) { type_ = p1; listvardecl_ = p2; }

  public <R,A> R accept(soottocfg.ast.Absyn.LVarStatement.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.LVar) {
      soottocfg.ast.Absyn.LVar x = (soottocfg.ast.Absyn.LVar)o;
      return this.type_.equals(x.type_) && this.listvardecl_.equals(x.listvardecl_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.listvardecl_.hashCode();
  }


}
