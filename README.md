# dashboard-skeleton

Squelette de projet hexagonal pour un tableau de bord de suivi :
- dossiers Windows scannés récursivement
- archives PST Outlook
- persistance SQLite en production
- H2 en test

## Modules principaux

- `domain` : règles métier et ports
- `application` : cas d'usage
- `infrastructure` : SQLite, filesystem, PST, scheduler
- `interfaceadapters` : REST et DTO
- `config` : assemblage Spring

## Remarques

Les classes de persistance et les adaptateurs techniques sont volontairement en `UnsupportedOperationException`
pour garder la structure prête à coder sans figer les choix d'implémentation.
