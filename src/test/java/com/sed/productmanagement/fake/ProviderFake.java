package com.sed.productmanagement.fake;

import com.sed.productmanagement.model.provider.Provider;

public class ProviderFake {
    public static Provider createProvider() {
        Provider provider = new Provider();
        provider.setName("name");
        provider.setCode("code");
        return provider;
    }
}
