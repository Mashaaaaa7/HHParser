"use client";

import React from "react";

import { Button } from "components/ui/button";
import { Input } from "components/ui/input";
import { Label } from "components/ui/label";
import { Popover, PopoverContent, PopoverTrigger } from "components/ui/popover";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue
} from "components/ui/select";
import { useSearchParams } from "next/navigation";

export const Filter = () => {
  const searchParams = useSearchParams();

  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button variant="outline">Настройки фильтрации</Button>
      </PopoverTrigger>
      <PopoverContent className="w-80" align="end">
        <form className="grid gap-4">
          <div className="space-y-2">
            <h4 className="font-medium leading-none">Найдем все</h4>
            <p className="text-sm text-muted-foreground">
              Подбери вакансию по своим требованиям
            </p>
          </div>
          <div className="grid gap-2">
            <div className="grid grid-cols-3 items-center gap-4">
              <Label htmlFor="query">Название</Label>
              <Input
                id="query"
                name="query"
                defaultValue={searchParams.get('query') ?? ''}
                placeholder="Программист"
                className="col-span-2 h-8"
              />
            </div>
            <div className="grid grid-cols-3 items-center gap-4">
              <Label htmlFor="city">Город</Label>
              <Input
                id="city"
                name="city"
                defaultValue={searchParams.get('city') ?? ''}
                placeholder="Москва"
                className="col-span-2 h-8"
              />
            </div>
            <div className="grid grid-cols-3 items-center gap-4">
              <Label htmlFor="salary">Зарплата от</Label>
              <Input
                id="salary"
                name="salary"
                type="number"
                defaultValue={searchParams.get('salary') ?? ''}
                placeholder="60000"
                className="col-span-2 h-8"
              />
            </div>
            <div className="grid grid-cols-3 items-center gap-4">
              <Label htmlFor="order">Сортировать</Label>
              <Select name="order_by" defaultValue={searchParams.get('order_by') ?? 'ASC'}>
                <SelectTrigger className="w-[185px] h-[32px]">
                  <SelectValue id="order" placeholder="Select a fruit" />
                </SelectTrigger>
                <SelectContent>
                  <SelectGroup>
                    <SelectItem value="ASC">
                      Возрастанию
                    </SelectItem>
                    <SelectItem value="DESC">
                      Убыванию
                    </SelectItem>
                  </SelectGroup>
                </SelectContent>
              </Select>
            </div>
            <div className="grid grid-cols-3 items-center gap-4">
              <Label htmlFor="limit">Лимит</Label>
              <Select name="limit" defaultValue={searchParams.get('limit') ?? '100'}>
                <SelectTrigger className="w-[185px] h-[32px]">
                  <SelectValue id="limit" placeholder="Select a fruit" />
                </SelectTrigger>
                <SelectContent>
                  <SelectGroup>
                    {[10, 50, 100].map((limit) => (
                      <SelectItem key={limit} value={String(limit)}>
                        {limit}
                      </SelectItem>
                    ))}
                  </SelectGroup>
                </SelectContent>
              </Select>
            </div>
            <Button
              className="mt-2"
              type="submit"
              size="sm"
            >
              Искать
            </Button>
          </div>
        </form>
      </PopoverContent>
    </Popover>
  );
};
