Este proyecto es una api, mi primera api la cual trata sobre una veterinaria que se encarga del CRUD de Mascotas y Dueños. 

Primero se crean el/los Dueños y luego se puede crear la Mascota, pero a la mascota se la crea de forma que se relaciona un dueño
mediante el id, este dueño debe si o si no tener previamente asociado otra mascota, ya que diseñe el sistema de forma que la 
relacion entre Mascotas y Dueños sea de Uno a Uno (tengo conocimiento de todas las variantes de relaciones 
que existen al crear registros y como funciona cada una). 

Se que en al vida real esto no es asi, ya que un Dueño puede tener varias mascotas y viceversa, pero al razon de haber 
creado el sistema de este modo es debido a que mi objetivo principal era aprender sobre el desarrollo de APIS con Spring Boot,
Docker, dockerizar esta app y subir este sistema a un servidor web, a su vez tambien subir la bd del sistema en otro servidor,
comprender la razon de porque es mejor separar la api y bd en distintos servidores.
