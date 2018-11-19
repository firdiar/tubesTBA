/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

/**
 *
 * @author Yulius Langobelen
 */
public class TransitionFA {
    private Character read;
    private StateFA next;

	public TransitionFA(Character read, StateFA next) {
		this.read = read;
		this.next = next;
	}

 

	public Character getRead() {
		return read;
	}

	

	public StateFA getNext() {
		return next;
        }
}
