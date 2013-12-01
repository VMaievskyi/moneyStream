package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piramida.controller.exception.AccountOperationException;
import com.piramida.entity.dto.WalletDtoList;
import com.piramida.facade.wallet.WalletFacade;

@RequestMapping(value = "/controller/wallets")
@Controller
public class WalletController {

    @Autowired
    private WalletFacade walletFacade;

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.PUT)
    public void addWalletsToAccount(@RequestBody final WalletDtoList wallets)
	    throws AccountOperationException {
	walletFacade.updateWallets(wallets);
    }

}
