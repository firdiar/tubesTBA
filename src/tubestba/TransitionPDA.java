/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

/**
 *
 * @author John
 */
public class TransitionPDA {
        private Character read;
	private Character pop;
	private String push;
	private StatePDA next;

	public TransitionPDA(Character read,Character pop, String push, StatePDA next) {
		this.read = read;
		this.pop = pop;
		this.push = push;
		this.next = next;
	}

	public Character getRead() {
		return read;
	}

	public Character getPop() {
		return pop;
	}

	public String getPush() {
		return push;
	}

	public StatePDA getNext() {
		return next;
        }
}
