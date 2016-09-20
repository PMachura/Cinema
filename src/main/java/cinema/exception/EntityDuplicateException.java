/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.exception;

/**
 *
 * @author Przemek
 */
public class EntityDuplicateException extends Exception{
    public EntityDuplicateException(String message){
        super(message);
    }
}
