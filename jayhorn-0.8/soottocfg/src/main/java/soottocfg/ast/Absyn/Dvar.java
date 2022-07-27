package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public class Dvar extends FieldDeclaration {
  public final Type type_;
  public final ListVarDecl listvardecl_;
  public Dvar(Type p1, ListVarDecl p2) { type_ = p1; listvardecl_ = p2; }

  public <R,A> R accept(soottocfg.ast.Absyn.FieldDeclaration.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof soottocfg.ast.Absyn.Dvar) {
      soottocfg.ast.Absyn.Dvar x = (soottocfg.ast.Absyn.Dvar)o;
      return this.type_.equals(x.type_) && this.listvardecl_.equals(x.listvardecl_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.listvardecl_.hashCode();
  }


}
