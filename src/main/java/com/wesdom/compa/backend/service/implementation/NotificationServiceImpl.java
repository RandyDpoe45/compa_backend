package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.model.*;
import com.wesdom.compa.backend.database.model.estatesegment.EstateSegment;
import com.wesdom.compa.backend.database.model.estatesegment.ProductInStateSegment;
import com.wesdom.compa.backend.database.model.users.*;
import com.wesdom.compa.backend.database.repositories.*;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IManufacturerRepository manufacturerRepository;

    @Autowired
    private IGroupRepository manufacturerGroupRepository;

    @Autowired
    private IEstateSegmentRepository estateSegmentRepository;

    @Autowired
    private IPromoterRepository promoterRepository;

    @Autowired
    private IAssociationRepository associationRepository;

    @Autowired
    private IProductInStateSegmentRepository productInStateSegmentRepository;

    private String MESSAGE_1 = "¡Hola %s! La familia %s se acaba de registrar en COMPA.";
    private String MESSAGE_2 = "¡Epa! El aliado comercial %s acaba de solicitar un pedido a tu organización. Revísalo en la sección \"Pedidos\".";
    private String MESSAGE_3 = "Compa, %s actualizó el pedido %d. Ve a la sección \"Mi asociación\" para revisar si hay novedades.";
    private String MESSAGE_4 = "Hola tu promotor ya actualizo tu oferta de producción y este es tu código de trazabilidad %s";
    private String MESSAGE_5 = "¡Hola %s! La familia %s, ha generado una alerta. Visítalos a ver qué fue lo que pasó.";
    private String MESSAGE_6 = "El compa %s se agregado a tu espacio de uso %s";
    private String MESSAGE_7 = "Hola, el compa %s se agregado a tu familia";
    private String MESSAGE_8 = "Hola, tus tareas de la visita del producto %s son: \n%s";
    private String MESSAGE_9 = "¡Hola %s! La familia %s de la vereda %s ya terminó de cosechar %s. Visítalos a ver cómo les fue.";
    private String MESSAGE_10 = "¡Hola %s! La familia %s de la vereda %s ya terminó de cosechar %s.";

    @Override
    public void createManufacturerGroupNotification(AssociationMember associationMember) {
        Notification notification = new Notification();
        notification.setEntity("ManufacturerGroup")
                .setEntityTarget("Association")
                .setMessage(
                        String.format(MESSAGE_1,
                                associationMember.getAssociation().getName(),
                                associationMember.getManufacturerGroup().getName()
                        )
                )
                .setIds(String.format("%d-", associationMember.getAssociation().getId()));
        notificationRepository.save(notification);
        associationMember
                .getAssociation()
                .getAssociationPromoterList()
                .forEach(x -> {
                    Notification notification1 = new Notification();
                    notification1.setEntity("ManufacturerGroup")
                            .setEntityTarget("Promoter")
                            .setMessage(
                                    String.format(MESSAGE_1,
                                            getUserName(x.getPromoter()),
                                            associationMember.getManufacturerGroup().getName()
                                    )
                            )
                            .setIds(String.format("%d-", x.getId()));
                    notificationRepository.save(notification1);
                });
    }

    @Override
    public void createRequestNotification(Request request) {
        Notification notification = new Notification();
        notification.setEntity("Request")
                .setEntityTarget("Association")
                .setMessage(String.format(MESSAGE_2, request.getCommercialPartner().getCompanyName()))
                .setIds(String.format("%d-", request.getAssociation().getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createRequestOfferNotification(RequestOffer requestOffer) {
        Notification notification = new Notification();
        notification.setEntity("RequestOffer")
                .setEntityTarget("ManufacturerGroup")
                .setMessage(
                        String.format(
                                MESSAGE_3,
                                requestOffer.getRequest().getAssociation().getName(),
                                requestOffer.getRequest().getId()
                        )
                )
                .setIds(String.format("%d-", requestOffer.getManufacturerGroup().getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createRequestOfferCodeNotification(RequestOffer requestOffer) {
        Notification notification = new Notification();
        notification.setEntity("RequestOffer")
                .setEntityTarget("ManufacturerGroup")
                .setMessage(String.format(MESSAGE_4, requestOffer.getDeliveryCode()))
                .setIds(String.format("%d-", requestOffer.getManufacturerGroup().getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createExpertAlertNotification(ExpertAlert expertAlert) {
        Notification notification = new Notification();
        String groupName = expertAlert.getProductInStateSegment()
                .getEstateSegment()
                .getEstate()
                .getManufacturerGroup().getName();
        notification.setEntity("ExpertAlert")
                .setEntityTarget("Promoter")
                .setMessage(
                        String.format(
                                MESSAGE_5,
                                getUserName(expertAlert.getPromoter()),
                                groupName
                        )
                )
                .setIds(String.format("%d-", expertAlert.getPromoter().getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createExpertVisitTaskNotification(ExpertVisit expertVisit) {
        Notification notification = new Notification();
        ManufacturerGroup manufacturerGroup = expertVisit.getProductInStateSegment()
                .getEstateSegment()
                .getEstate()
                .getManufacturerGroup();
        String productName = expertVisit.getProductInStateSegment().getProduct().getName();
        notification.setEntity("ExpertVisit")
                .setEntityTarget("ManufacturerGroup")
                .setMessage(String.format(MESSAGE_8, productName, expertVisit.getFutureTask()))
                .setIds(String.format("%d-", manufacturerGroup.getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createMGEstateSegmentNotification(Long estateSegmentId, Long manufacturerId) {
        EstateSegment estateSegment = estateSegmentRepository.get(estateSegmentId);
        Manufacturer manufacturer = manufacturerRepository.get(manufacturerId);
        Notification notification = new Notification();
        String manufacturerName = Objects.toString(manufacturer.getFirstName(), "")
                + " "
                + Objects.toString(manufacturer.getFirstLastname(), "");
        notification.setEntity("EstateSegment")
                .setEntityTarget("ManufacturerGroup")
                .setMessage(
                        String.format(MESSAGE_6, manufacturerName, estateSegment.getName()))
                .setIds(String.format("%d-", estateSegment.getEstate().getManufacturerGroup().getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createMGManufacturerNotification(Long manufacturerGroupId, Long manufacturerId) {
        ManufacturerGroup manufacturerGroup = manufacturerGroupRepository.get(manufacturerGroupId);
        Manufacturer manufacturer = manufacturerRepository.get(manufacturerId);
        Notification notification = new Notification();
        String manufacturerName = Objects.toString(manufacturer.getFirstName(), "")
                + " "
                + Objects.toString(manufacturer.getFirstLastname(), "");
        notification.setEntity("Manufacturer")
                .setEntityTarget("ManufacturerGroup")
                .setMessage(String.format(MESSAGE_7, manufacturerName))
                .setIds(String.format("%d-", manufacturerGroup.getId()));
        notificationRepository.save(notification);
    }

    @Override
    public void createProductionFinishedNotification(Long productInStateSegmentId, Long promoterId, Long associationId) {
        ProductInStateSegment productInStateSegment = productInStateSegmentRepository.get(productInStateSegmentId);
        Promoter promoter = promoterRepository.get(promoterId);
        Association association = associationRepository.get(associationId);
        Notification notification = new Notification();
        String groupName = productInStateSegment
                .getEstateSegment()
                .getEstate()
                .getManufacturerGroup()
                .getName();
        notification.setEntity("ProductInStateSegment")
                .setEntityTarget("Association")
                .setMessage(
                        String.format(
                                MESSAGE_10,
                                association.getName(),
                                groupName,
                                Objects.toString(productInStateSegment.getEstateSegment().getEstate().getPlantation(), ""),
                                productInStateSegment.getProduct().getName()))
                .setIds(String.format("%d-", association.getId()));
        notificationRepository.save(notification);

        notification = new Notification();
        notification.setEntity("ProductInStateSegment")
                .setEntityTarget("Promoter")
                .setMessage(
                        String.format(
                                MESSAGE_9,
                                getUserName(promoter),
                                groupName,
                                Objects.toString(productInStateSegment.getEstateSegment().getEstate().getPlantation(), ""),
                                productInStateSegment.getProduct().getName()
                        )
                )
                .setIds(String.format("%d-", promoter.getId()));
        notificationRepository.save(notification);
    }

    private String getUserName(BaseUser baseUser){
        return Objects.toString(baseUser.getFirstName(), " ")
                + " " + Objects.toString(baseUser.getFirstLastname(), " ");
    }
}
