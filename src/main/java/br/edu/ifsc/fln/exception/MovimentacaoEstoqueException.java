package br.edu.ifsc.fln.exception;

@SuppressWarnings("SpellCheckingInspection")
public class MovimentacaoEstoqueException extends Exception{

    /**
     * Creates a new instance of <code>MovimentacaoEstoqueException</code>
     * without detail message.
     */
    public MovimentacaoEstoqueException() {
    }

    /**
     * Constructs an instance of <code>MovimentacaoEstoqueException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public MovimentacaoEstoqueException(String msg) {
        super(msg);
    }
}
