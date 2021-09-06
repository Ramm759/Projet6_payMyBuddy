package com.mycompany.paymybuddy.service;

import com.mycompany.paymybuddy.dto.ExternalTransferDTO;
import com.mycompany.paymybuddy.dto.InternalTransferDTO;

import java.sql.SQLException;
import java.util.List;

public interface ITransferService {
    InternalTransferDTO createInternalTransfer (InternalTransferDTO internalTransferDTO) throws SQLException; // on renvoie le Dto avec Id
    ExternalTransferDTO createExternalTransfer (ExternalTransferDTO externalTransferDTO) throws SQLException;
    List<InternalTransferDTO> listInternalTransfer (String emailOwner);
    List<ExternalTransferDTO> listExternalTransfer (String emailOwner);

}
