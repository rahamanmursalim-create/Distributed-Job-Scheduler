# Develop docs

## Kite

### Generate entity,repo,dto,dao,data:

```shell
cd ../distributedjobscheduler-server/distributedjobscheduler-server-repository
kite plug run 'gen java ser code' -- --cs @c --tpl distributedjobscheduler/entity,repo,dto,distributedjobscheduler/dao,distributedjobscheduler/dao-impl,distributedjobscheduler/data,distributedjobscheduler/data-impl --pkg io.distributedjobscheduler.server.repository -o @pkg
```

### Generate req,vo,controller,service dto:

```shell
cd ../distributedjobscheduler-server/distributedjobscheduler-server-admin
kite plug run 'gen java ser code' -- --cs @c --tpl curd,distributedjobscheduler/controller,distributedjobscheduler/service,distributedjobscheduler/service-impl --pkg io.distributedjobscheduler.server.admin -o @pkg

kite plug run 'gen java ser code' -- --cs @c --tpl distributedjobscheduler/service,distributedjobscheduler/service-impl --pkg io.distributedjobscheduler.server.admin -o @pkg

kite plug run 'gen java ser code' -- --cs @c --tpl distributedjobscheduler/controller --pkg io.distributedjobscheduler.server.admin -o @pkg
```
