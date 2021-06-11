package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService implements ITransferService{

    @Override
    public InternalTransferDTO createInternalTransfer(InternalTransferDTO internalTransferDTO) {
        return null;
    }

    @Override
    public ExternalTransferDTO createExternalTransfer(ExternalTransferDTO externalTransferDTO) {
        return null;
    }

    @Override
    public List<InternalTransferDTO> listInternalTransfer(String emailOwner) {
        return null;
    }

    @Override
    public List<ExternalTransferDTO> listExternalTransfer(String emailOwner) {
        return null;
    }
}
