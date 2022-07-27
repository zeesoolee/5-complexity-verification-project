package soottocfg.ast.Absyn; // Java Package generated by the BNF Converter.

public abstract class TypeName implements java.io.Serializable {
  public abstract <R,A> R accept(TypeName.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(soottocfg.ast.Absyn.BuiltIn p, A arg);
    public R visit(soottocfg.ast.Absyn.ClassType p, A arg);

  }

}
