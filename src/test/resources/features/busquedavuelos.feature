Feature: Búsqueda de vuelos en LATAM

  @BusquedaVuelos
  Scenario: Búsqueda solo ida internacional desde CSV
    Given el usuario accede a la página de LATAM
    When busca un vuelo solo ida de "<origen>" a "<destino>" con fecha válida
    Then el sistema debe mostrar vuelos disponibles


@BusquedaVuelos
Scenario: Búsqueda ida y vuelta nacional desde CSV
  Given el usuario accede a la página de LATAM
  When busca un vuelo ida y vuelta de "<origen>" a "<destino>" con fechas válidas
  Then el sistema debe mostrar los vuelos disponibles

 @BusquedaVuelos
  Scenario: Fecha de regreso anterior a la de ida
    Given el usuario accede a la página de LATAM
    When intenta seleccionar una fecha de regreso anterior a la de ida
    Then el sistema debe mostrar un mensaje de validación