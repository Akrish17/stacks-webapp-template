# stacks-webapp-template

# ![stacks-webapp-template](.github/images/stacks_logo.png)

## To start

`yarn` on the root of the application to install all the dependencies.

`yarn lerna:setup` to bootstrap the packages in the current Lerna repo. Installs
all of their dependencies and links any cross-dependencies.

## Tests

##### Static

`yarn lint`: runs global linting from root level, ensuring all packages are
following conventions specified in [.eslintrc](.eslintrc).

`yarn prettier`: runs formatting from root level, ensuring all packages are
following conventions specified in [.prettierrc](.prettierrc).

`yarn validate`: ensures that the project adheres to Typescript checks,
formatting and linting rules.

## Web Application

To run the web application from the root:

```bash
# To run locally:
yarn dev
```

> To read more about the webapp click [here](./packages/webapp/README.md)

### Node version

We are supporting and running [node@12](https://nodejs.org/en/about/releases/).
Please ensure that your local environment has the correct version
[installed](https://nodejs.org/en/download/).
