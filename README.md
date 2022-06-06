# Acquiring weather data using Scala
The purpose of this project is to obtain meteorological data from DMI in order to get an overview of the weather for some historical period.

DMI has an available REST-API: https://confluence.govcloud.dk/pages/viewpage.action?pageId=26476690

# Quick start (Linux):
1. git clone repository
2. change directory to cloned repository
3. set environments, eg. `source parameters.sh` (for overview look at [ClimateParameterNames.scala](./src/main/scala/weather/ClimateParameterNames.scala) and [Parameters.scala](./src/main/scala/weather/Parameters.scala))
4. set API_KEY environment variable
5. run using `sbt run`

## Defining the period

## Parameters
- PARAMETER_ID
- TIME_RESOLUTION
- STATION_ID
- LIMIT
- START_DATE
- END_DATE
- DATE

