package com.piramida.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slava
 * Date: 10/20/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class WalletPK implements Serializable {
    private int id;
    private int owner;

@Id
@Column(name = "id")
public int getId() {
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    @Id@Column(name = "owner")
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletPK walletPK = (WalletPK) o;

        if (id != walletPK.id) return false;
        if (owner != walletPK.owner) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + owner;
        return result;
}}
