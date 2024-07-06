import React, { FunctionComponent } from "react";

import {
  Card as UICard,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle
} from "components/ui/card";

import { Filter } from "./_components";

import { CardProps } from "./types";

export const Card: FunctionComponent<CardProps> = (
  { title, description, children }
) => (
  <UICard
    className="xl:col-span-2 m-8"
    x-chunk="dashboard-01-chunk-4"
  >
    <CardHeader className="flex flex-row items-center justify-between">
      <div className="grid gap-2">
        <CardTitle>{title}</CardTitle>
        <CardDescription>{description}</CardDescription>
      </div>
      <Filter />
    </CardHeader>
    <CardContent>
      {children}
    </CardContent>
  </UICard>
);
