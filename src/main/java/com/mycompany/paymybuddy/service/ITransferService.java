package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;

import java.util.List;

public interface ITransferService {
    InternalTransferDTO createInternalTransfer (InternalTransferDTO internalTransferDTO); // on renvoie le Dto avec Id
    ExternalTransferDTO createExternalTransfer (ExternalTransferDTO externalTransferDTO);
    List<InternalTransferDTO> listInternalTransfer (String emailOwner);
    List<ExternalTransferDTO> listExternalTransfer (String emailOwner);
    }
