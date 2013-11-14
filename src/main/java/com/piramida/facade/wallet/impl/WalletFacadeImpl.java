package com.piramida.facade.wallet.impl;

import java.util.Set;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piramida.entity.Wallet;
import com.piramida.entity.dto.WalletDtoList;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
import com.piramida.facade.wallet.WalletFacade;
import com.piramida.service.wallet.WalletService;

@Service
public class WalletFacadeImpl implements WalletFacade {

    @Autowired
    private MapperFactory mapperFactory;
    @Autowired
    private WalletService walletService;

    @Override
    public void updateWallets(final WalletDtoList walletsDto) {
	Validate.notNull(walletsDto, "null wallet list passed");
	// Get account here

	final AbstractDtoEntityMapper mapper = mapperFactory
		.createInstance(walletsDto.getClass());
	final Set<Wallet> wallets = (Set<Wallet>) mapper.map(walletsDto);
	walletService.updateWallets(wallets, null);
    }

}
